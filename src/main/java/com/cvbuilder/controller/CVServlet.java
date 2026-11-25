package com.cvbuilder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.cvbuilder.model.CVBean;


@MultipartConfig(maxFileSize = 5242880, maxRequestSize = 10485760)   
public class CVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

       
        CVBean cv = new CVBean();
        cv.setFullName(request.getParameter("fullName"));
        cv.setProfessionalTitle(request.getParameter("professionalTitle"));
        cv.setEmail(request.getParameter("email"));
        cv.setPhone(request.getParameter("phone"));
        cv.setProfileUrl(request.getParameter("profileUrl"));
        cv.setProfileSummary(request.getParameter("profileSummary"));
        cv.setDegree(request.getParameter("degree"));
        cv.setInstitution(request.getParameter("institution"));
        cv.setGraduationYear(request.getParameter("graduationYear"));
        cv.setMajor(request.getParameter("major"));
        cv.setJobTitle(request.getParameter("jobTitle"));
        cv.setCompany(request.getParameter("company"));
        cv.setDuration(request.getParameter("duration"));
        cv.setResponsibilities(request.getParameter("responsibilities"));
        cv.setTechnicalSkills(request.getParameter("technicalSkills"));
        cv.setSoftSkills(request.getParameter("softSkills"));

       
        Part photo = request.getPart("photo");
        if (photo != null && photo.getSize() > 0 && photo.getSize() <= 2097152
                && photo.getContentType() != null
                && photo.getContentType().startsWith("image/")) {
            try (InputStream in = photo.getInputStream()) {
                byte[] bytes = in.readAllBytes();
                cv.setPhoto("data:" + photo.getContentType() + ";base64,"
                        + Base64.getEncoder().encodeToString(bytes));
            }
        }                                                             

        
        if (cv.validate()) {
            request.setAttribute("cvData", cv);
            forward(request, response, "cvView.jsp");
        } else {
            request.setAttribute("cvData", cv);   // so the error page can read getErrors()
            request.setAttribute("errorMessage", "Please correct the following problems:");
            forward(request, response, "cvError.jsp");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("sample".equals(action)) {
            request.setAttribute("cvData", createSampleCV());
            request.setAttribute("isSample", true);
            forward(request, response, "cvView.jsp");
        } else {
            forward(request, response, "cvForm.jsp");
        }
    }

  
    private CVBean createSampleCV() {
        CVBean cv = new CVBean();
        cv.setFullName("Aisyah Binti Rahman");
        cv.setProfessionalTitle("Junior Software Engineer");
        cv.setEmail("aisyah.rahman@example.com");
        cv.setPhone("+60 12-345 6789");
        cv.setProfileUrl("https://github.com/aisyahrahman");
        cv.setProfileSummary("Motivated computer science graduate with hands-on experience in "
                + "Java EE web development and a passion for building clean, maintainable applications.");
        cv.setDegree("Bachelor of Computer Science (Hons.)");
        cv.setInstitution("Universiti Teknologi MARA");
        cv.setGraduationYear("2025");
        cv.setMajor("Software Engineering");
        cv.setJobTitle("Software Developer Intern");
        cv.setCompany("TechNusa Sdn. Bhd.");
        cv.setDuration("Jun 2024 - Dec 2024");
        cv.setResponsibilities("Developed REST APIs using Java Servlets and JSP\n"
                + "Reduced page load time by 30% through query optimisation\n"
                + "Collaborated with a team of 5 using Git and Agile Scrum");
        cv.setTechnicalSkills("Java, JSP, Servlets, MySQL, HTML, CSS, JavaScript, Git");
        cv.setSoftSkills("Teamwork, Communication, Problem Solving, Time Management");
        return cv;
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}