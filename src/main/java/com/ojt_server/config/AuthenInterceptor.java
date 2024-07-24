package com.ojt_server.config;

import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.util.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AuthenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        // Thực thi trước khi request vào controller
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // Thực thi trước khi request vào controller

        String token = request.getHeader("token");
        System.out.println(token+"token");
        UserModel user = JwtService.verifyTokenUser(token);

        if (user == null) {
            response.setStatus(401);

            return false;
        }

        JedisPool jedisPool = new JedisPool("localhost", 6379);
        try (Jedis jedis = jedisPool.getResource()) {

            String tokenRedis = jedis.get(String.valueOf(user.getId()));

            if(!tokenRedis.equals(token)) {

                response.setStatus(401);

                return false;
            }
        }
        jedisPool.close();


        request.setAttribute("data", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Thực thi sau khi controller xử lý request, trước khi trả về response
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Thực thi sau khi response đã được trả về client
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
