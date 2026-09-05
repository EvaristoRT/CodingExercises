public class SwapValues {
    public static void main(String[] args) {
        /*Instrucción
        I would like to be able to pass an array with two elements to my function to swap the values. However it appears that the values aren't changing.

        Can you figure out what's wrong here?
        class Swapper{

            public Object[] arguments;
            public Swapper(final Object[] args){
                arguments=args;
            }
            public void swapValues() {
                Object[] args = new Object[]{arguments[0],arguments[1]};
                Object temp = args[0];
                args[0] = args[1];
                args[1] = temp;
            }
        }
        */
    }
    /*Solución
    class Swapper{

    public Object[] arguments;

    public Swapper(final Object[] args){
        arguments=args;
    }

    public void swapValues() {
        Object temp = arguments[0];
        arguments[0] = arguments[1];
        arguments[1] = temp;
    }
}
    */
    
}
