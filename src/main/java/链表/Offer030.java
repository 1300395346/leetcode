package 链表;

import struct.RandomizedSet;

public class Offer030 {
    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        System.out.println(set.insert(1));
        System.out.println(set.remove(2));
        System.out.println(set.insert(2));
        System.out.println(set.remove(1));
        System.out.println(set.getRandom());
    }
}
