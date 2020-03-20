package com.example.base.springmvc;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WebContextFilter implements Filter {

  public WebContextFilter() {
  }

  @Override
  public void destroy() {

  }

  /**
   * 在进入时将request和response注册到WebContext中，结束时清除
   *
   * @param request 要注入的request
   * @param response 要注入的response
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException,
      ServletException {
    /*
     * HttpServletResponse httpResponse = (HttpServletResponse) response;
     * httpResponse.setHeader("Pragma","No-cache"); httpResponse.setHeader("Cache-Control","no-cache");
     * httpResponse.setHeader("Expires","0");
     */
    try {
      HttpServletRequest req = (HttpServletRequest) request;

      req.setAttribute("ctx", req.getContextPath());
      WebContext.registry(req, (HttpServletResponse) response);

      chain.doFilter(request, response);
    } finally {
      WebContext.release();
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {

  }

}
