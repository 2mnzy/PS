package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      HttpSession session = request.getSession();
      session.invalidate();
      forward.setPath("index.jsp");
      forward.setRedirect(true);
      return forward;
   }

}