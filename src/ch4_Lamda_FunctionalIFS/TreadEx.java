package ch4_Lamda_FunctionalIFS;

public class TreadEx {
    public static void main(String[] args) {
        // 익명 클래스
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        thread.run();
        // 람다 표현식
        Thread thread2=new Thread(() -> System.out.println("World"));
        thread2.start();

        // 재사용성 고려
        Runnable runImpl=() -> System.out.println("hello world");
        Thread t1=new Thread(runImpl);
        t1.start();
        // 재사용성 응용

        Runnable runImpl2=getRunable();
        runImpl2.run();
    }
    static Runnable getRunable(){
        return ()-> System.out.println("hello world !");
    }
}
