    package cn.bing.aspectj.annotation;

    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.*;
    import org.springframework.stereotype.Component;

    @Aspect
    @Component
    public class MyAspect {
        @Pointcut("execution(* cn.bing.common.*.*(..))")
        private void myPointCut(){}

        @Before("myPointCut()")
        public void myBefore(JoinPoint joinPoint){
            System.out.println("ǰ��֪ͨ: ģ��ִ��Ȩ�޼��... ,");
            System.out.print("Ŀ����: "+joinPoint.getTarget());
            System.out.println(" , ��ֲ����ǿ�����Ŀ�귽��:"+joinPoint.getSignature().getName());
        }

        @AfterReturning(value = "myPointCut()")
        public void myAfterReturning(JoinPoint joinPoint){
            System.out.println("����֪ͨ: ģ���¼��־ ...");
            System.out.println("��ֲ����ǿ�����Ŀ�귽��Ϊ: "+joinPoint.getSignature().getName());
        }

        @Around("myPointCut()")
        public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
            System.out.println("���ƿ�ʼ: ִ��Ŀ�귽��֮ǰ, ģ�⿪������ .... ");
            Object object = proceedingJoinPoint.proceed();
            System.out.println("���ƽ���: ִ��Ŀ�귽��֮��, ģ��ر����� ....");
            return object;
        }

        @AfterThrowing(value = "myPointCut()", throwing = "e")
        public void myAfterThrowing(JoinPoint joinPoint, Throwable e){
            System.out.println("�쳣֪ͨ: "+"�����쳣" + e.getMessage());
        }

        @After(value = "myPointCut()")
        public void myAfter(){
            System.out.println("����֪ͨ: ģ�ⷽ���������ͷ���Դ ...");
        }



    }



