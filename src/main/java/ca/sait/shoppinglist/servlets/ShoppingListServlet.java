package ca.sait.shoppinglist.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jason
 */
public class ShoppingListServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        Get the name from the session
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
//      Logout feature
        String query = request.getQueryString();              
        if (query != null && query.contains("logout")){
            session.invalidate();    
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);            
        } else {  
            response.sendRedirect("ShoppingList");
            return;
        } 

        
//        If name doesn't exists, send it to the register page.
//        If name exists, send it to the shopping list page.
        if (name == null){
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } 
        


              
    }

          
        
       
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                    
//      Get the session object
        HttpSession session = request.getSession();                    
        
        String action = request.getParameter("action");               
                
//      If the action is add, it is coming from the shopping list form
//      If the action is not add, it is coming from the register page
        if (action != null && action.equals("add")){
            
//          Get the item input
            String item = request.getParameter("item");
            
//          Add the item to the session
//          Pulls arraylist
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
            
//          Add item to arraylist
            items.add(item);
            
//          Push the arraylist back into the session
            session.setAttribute("items", items);
            
            
//      Checks if delete form is submitted
        } else if (action != null && action.equals("delete")) {
            String item = request.getParameter("item");
            
//          Delete the item from the session
//          Pulls arraylist
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
            
//          Delete item from arraylist
            items.remove(item);
            
//          Push the arraylist back into the session
            session.setAttribute("items", items);
            
        } else{
//          Get the name from the input in register.jsp
            String name = request.getParameter("name");
            
            

//          When the user registers, create an empty arraylist
            ArrayList<String> items = new ArrayList<>();
            
//          Send the name into the session
            session.setAttribute("name", name);
            
            session.setAttribute("items", items);
            
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);

    }

}
