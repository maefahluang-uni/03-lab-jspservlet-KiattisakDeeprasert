package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String weightStr = request.getParameter("weight");
        String heightStr = request.getParameter("height");

        if (weightStr != null && !weightStr.isEmpty() && heightStr.isEmpty()) {
            try {
                 double weight = Double.parseDouble(weightStr);
                 double height = Double.parseDouble(heightStr);
         
                 //TODO: calculate bmi
                 double bmi = calculateBMI(weight, height);

                 //TODO: determine the built from BMI
                 String build = determineBuild(bmi);
                  //TODO: add bmi and built to the request's attribute
                 request.setAttribute("bmi", Math.round(bmi));
                 request.setAttribute("build", build);
                 //TODO: forward to jsp
                 request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);

            } catch (Exception e) {
                // TODO: handle exception
                response.getWriter().println("Invalid weight or hei values");
            }
            
        } else {
            response.getWriter().println("please provide weight and height parameters");
        }
       
    }
     private double calculateBMI(double weight, double height){
         return weight / ((height)*(height));   
        }
      
        private String determineBuild(double bmi){
            if(bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "normal weight";
        } else if (bmi <30) {
            return "overweight";
        } else if(bmi < 35){
            return "obese";

        } 
         else {
            return "extremely obese";
        }
      
        
           
    }
    
}
