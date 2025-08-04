package it.simo.aulab_post.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.CareerRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class NotificationInterceptor implements HandlerInterceptor {

    @Autowired
    CareerRequestRepository careerRequestRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && request.isUserInRole("ROLE_ADMIN")) {
            int careerCount = careerRequestRepository.findByIsCheckedFalse().size();
            modelAndView.addObject("careerRequests", careerCount);
        }
        if(modelAndView !=null && request.isUserInRole("ROLE_REVISOR")){
            int revisedCount=articleRepository.findByIsAcceptedNull().size();
            modelAndView.addObject("articleToBeRevised", revisedCount);
        }
    }
}
