package ru.vsu.csf.Sashina;

import java.util.Iterator;

public class Logic {

    static double EPS = 1E-9;

    public static LinkedListNum<Double> fillLinkedList (String s) throws NumberFormatException {
        try {
            if (s.isEmpty()) {
                return null;
            } else {
                LinkedListNum<Double> LinkedList = new LinkedListNum<>();
                String [] str = s.split("[{,\\s\\:?}]");
                for (String value : str) {
                    LinkedList.addAtTheEnd(Double.parseDouble(value));
                }
                return LinkedList;
            }
        } catch (Exception error) {
            return null;
        }
    }

    public static LinkedListNum<Double> solution (LinkedListNum<Double> l1, LinkedListNum<Double> l2) {
        try {
            if (!l1.isSorted() || !l2.isSorted()) {
                return null;
            } else {
                LinkedListNum<Double> newList = new LinkedListNum<>();
                Iterator<Double> it1 = l1.iterator();
                Iterator<Double> it2 = l2.iterator();
                double i = it1.next();
                double j = it2.next();
                double pred = Double.MIN_VALUE;
                while (it1.hasNext() && it2.hasNext()) {
                    if (i - j > 0) {
                        j = it2.next();
                    } else if (Math.abs(i - j) <= EPS) {
                        if (Math.abs(i - pred) > EPS) {
                            pred = i;
                            newList.addAtTheEnd((i));
                        }
                        i = it1.next();
                        j = it2.next();
                    } else {
                        i = it1.next();
                    }
                }
                if (Math.abs(i - j) <= EPS) {
                    if (!newList.contains(i)) {
                        newList.addAtTheEnd(i);
                    }
                }
                return newList;
            } // O(l1 + l2)
        } catch (Exception exp) {
            return null;
        }
    }

    public static String listToString (LinkedListNum<Double> list) {
        try {
            if (list.isEmpty()) {
                return null;
            } else {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    s.append(list.getValue(i));
                    s.append("  ");
                }
                return s.toString();
            }
        } catch (Exception exp) {
            return null;
        }
    }
}
