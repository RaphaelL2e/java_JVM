public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVA_HOOK =null;

    public void alive(){
        System.out.println(" yes,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVA_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVA_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVA_HOOK = null;
        System.gc();
        //由于finalize()优先级很低暂停一下等待
        Thread.sleep(500);
        if(SAVA_HOOK!=null){
            SAVA_HOOK.alive();
        }else {
            System.out.println("no,i am dead:(");
        }

        SAVA_HOOK = null;
        System.gc();
        //由于finalize()优先级很低暂停一下等待
        Thread.sleep(500);
        if(SAVA_HOOK!=null){
            SAVA_HOOK.alive();
        }else {
            System.out.println("no,i am dead:(");
        }
    }
}
