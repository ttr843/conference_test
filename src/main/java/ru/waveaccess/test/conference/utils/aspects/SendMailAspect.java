package ru.waveaccess.test.conference.utils.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.services.MailService;
import ru.waveaccess.test.conference.utils.annotations.MailType;
import ru.waveaccess.test.conference.utils.annotations.SendMail;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class SendMailAspect {
    private final MailService mailService;

    @Pointcut("@annotation(ru.waveaccess.test.conference.utils.annotations.SendMail)")
    public void sendMailPointcut() {
    }

    @Around(value = "sendMailPointcut()")
    public Object sendMail(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SendMail sendMail = method.getAnnotation(SendMail.class);
        MailType mailType = sendMail.mailType();
        Object returnedValue = null;
        try {
            returnedValue = proceedingJoinPoint.proceed();
        } finally {
            if (returnedValue != null) {
                switch (mailType) {
                    case CONFIRM: {
                        UserDto userDto = (UserDto) returnedValue;
                        mailService.confirmMail(mailType.toString(), userDto.getConfirmCode(),
                                userDto.getEmail());
                    }
                }
            }
        }
        return returnedValue;
    }
}
