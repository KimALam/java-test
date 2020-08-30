package test.instance_getclass;

import java.util.Objects;

class InstanceGetClassTest {
    public static void main(String[] args) {
        Parent p1 = new InstanceChild();
        p1.setA(1);
        ((InstanceChild)p1).setBbb(222);
        Parent p2 = new GetClassChild();
        p2.setA(1);

        Parent p3 = new InstanceChild();
        p3.setA(1);
        ((InstanceChild)p3).setBbb(111);

        System.out.println("p1.equal(p2): " + p1.equals(p2));
        System.out.println("p2.equal(p1): " + p2.equals(p1));
        System.out.println("p1.equal(p3): " + p1.equals(p3));
    }

    private static class Parent {
        private int a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Parent parent = (Parent) o;
            return a == parent.a;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a);
        }

        public int getA() { return a; }
        public void setA(int a) { this.a = a; }
    }


    private static class InstanceChild extends Parent {
        private int bbb;

        public int getBbb() { return bbb; }
        public void setBbb(int bbb) { this.bbb = bbb; }
    }

    private static class GetClassChild extends Parent {
        private int ccc;

        public int getCcc() { return ccc; }
        public void setCcc(int ccc) { this.ccc = ccc; }
    }
}
