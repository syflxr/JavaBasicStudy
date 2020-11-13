import org.junit.Test;

/**
 * @Auther: shenyafeng
 * @Date: 2020/11/13 10:18
 * @Description:面向对象相关的测试
 */
public class OOPTest {
    static class A{
        protected int value;
        public A(int v){

        }
        public void setValue(int value){
            this.value=value;
        }
        public int getValue(){
            try{
                value++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);;
            }
        }
    }

    static class B extends A{
        public B(){
            super(5);
            setValue(getValue()-3);
        }
        public void setValue(int value){
            super.setValue(2*value);
        }
    }

    @Test
    public void oopTest1(){
        System.out.println(new B().getValue());
    }
}
