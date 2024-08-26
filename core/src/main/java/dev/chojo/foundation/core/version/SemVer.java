/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) EldoriaRPG Team and Contributor
 */

package dev.chojo.foundation.core.version;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record SemVer(String version, List<Integer> nums) implements Comparable<SemVer> {
    public static final int MAJOR = 0;
    public static final int MINOR = 1;
    public static final int PATCH = 2;
    private static final Pattern NUMBER = Pattern.compile("([0-9]+)");

    public static SemVer parse(String version) {
        List<Integer> nums = new ArrayList<>();

        Matcher matcher = NUMBER.matcher(version);
        while (matcher.find()) {
            nums.add(Integer.parseInt(matcher.group(1)));
        }

        return new SemVer(version, nums);
    }

    public static SemVer of(Integer... nums) {
        return of(Arrays.stream(nums).toList());
    }

    public static SemVer of(List<Integer> nums) {
        return new SemVer(nums.stream().map(String::valueOf).collect(Collectors.joining(".")), nums);
    }

    @Override
    public List<Integer> nums() {
        return Collections.unmodifiableList(nums);
    }

    public boolean isOlder(SemVer version) {
        return compareTo(version) < 0;
    }

    public boolean isOlderOrEqual(SemVer version) {
        return compareTo(version) <= 0;
    }

    public boolean isNewer(SemVer version) {
        return compareTo(version) > 0;
    }

    public boolean isNewerOrEqual(SemVer version) {
        return compareTo(version) >= 0;
    }

    public boolean isEqual(SemVer version) {
        return compareTo(version) == 0;
    }

    public boolean isBetweenInclusive(SemVer lower, SemVer upper) {
        return isNewer(lower) && isOlder(upper);
    }

    /**
     * Compares the version to a lower and upper version
     *
     * @param lower lower version (inclusive)
     * @param upper upper version (exclusive)
     * @return true if the version is between
     */
    public boolean isBetween(SemVer lower, SemVer upper) {
        return isNewerOrEqual(lower) && isOlder(upper);
    }

    public Comparator<String> comparator() {
        return Comparator.comparing(SemVer::parse);
    }

    public SemVer trim(int num) {
        return SemVer.of(nums.subList(0, Math.min(nums.size(), num)).toArray(Integer[]::new));
    }

    public SemVer set(int index, int value) {
        var newNums = new ArrayList<>(nums);
        if (newNums.size() < index) {
            newNums.set(index, value);
        } else {
            newNums.add(value);
        }
        return SemVer.of(newNums);
    }

    public SemVer increase(int index, int value) {
        var newNums = new ArrayList<>(nums);
        newNums.set(index, newNums.get(index) + value);
        return SemVer.of(newNums);
    }

    public SemVer decrease(int index, int value) {
        var newNums = new ArrayList<>(nums);
        newNums.set(index, Math.max(newNums.get(index) - value, 0));
        return SemVer.of(newNums);
    }

    @Override
    public String toString() {
        return version;
    }

    public int size() {
        return nums.size();
    }

    @Override
    public int compareTo(@NotNull SemVer version) {
        int numbers = Math.max(version.nums().size(), nums().size());
        for (int i = 0; i < numbers; i++) {
            int compare = Integer.compare(nums().size() > i ? nums().get(i) : 0,
                    version.nums().size() > i ? version.nums().get(i) : 0);
            if (compare != 0) return compare;
        }
        return 0;
    }
}
