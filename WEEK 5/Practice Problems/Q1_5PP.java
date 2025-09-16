package com.company.security;

public class Q1_5PP {
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;

    private void privateMethod() {
        System.out.println("Private method called");
    }
    void defaultMethod() {
        System.out.println("Default method called");
    }
    protected void protectedMethod() {
        System.out.println("Protected method called");
    }
    public void publicMethod() {
        System.out.println("Public method called");
    }

    public Q1_5PP(int p, String d, double pr, boolean pub) {
        privateField = p;
        defaultField = d;
        protectedField = pr;
        publicField = pub;
    }

    public void testInternalAccess() {
        System.out.println(privateField);

        System.out.println(defaultField);
        System.out.println(protectedField);
        System.out.println(publicField);
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        Q1_5PP obj = new Q1_5PP(10, "Default", 3.14, true);
        obj.testInternalAccess();
        System.out.println(obj.publicField);
        obj.publicMethod();
    }
}

class SamePackageTest {
    public static void testAccess() {
        Q1_5PP obj = new Q1_5PP(1, "A", 2.5, false);
        System.out.println(obj.defaultField);
        System.out.println(obj.protectedField);
        System.out.println(obj.publicField);
        obj.defaultMethod();
        obj.protectedMethod();
        obj.publicMethod();
    }
}