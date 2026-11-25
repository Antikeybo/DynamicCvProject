package com.cvbuilder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CVBean implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private String fullName;
    private String professionalTitle;
    private String email;
    private String phone;
    private String profileUrl;      
    private String profileSummary;

    
    private String degree;
    private String institution;
    private String graduationYear;
    private String major;

    
    private String jobTitle;
    private String company;
    private String duration;
    private String responsibilities;

   
    private String technicalSkills; 
    private String softSkills;      
    private String photo;
    
    private List<String> errors = new ArrayList<>();

    
    public CVBean() { }

    
    public boolean validate() {
        errors.clear();

        if (isBlank(fullName)) {
            errors.add("Full Name is required.");
        }
        if (isBlank(email)) {
            errors.add("Email is required.");
        } else if (!email.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            errors.add("Email format is invalid (example: name@example.com).");
        }
        if (isBlank(degree)) {
            errors.add("Highest Degree is required.");
        }
        if (isBlank(technicalSkills) && isBlank(softSkills)) {
            errors.add("At least one skill (technical or soft) is required.");
        }
        if (!isBlank(graduationYear) && !graduationYear.trim().matches("\\d{4}")) {
            errors.add("Year of Graduation must be a 4-digit year.");
        }
        return errors.isEmpty();
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    
    private List<String> split(String text, String regex) {
        List<String> list = new ArrayList<>();
        if (text != null) {
            for (String s : text.split(regex)) {
                if (!s.trim().isEmpty()) {
                    list.add(s.trim());
                }
            }
        }
        return list;
    }

    
    public List<String> getTechnicalSkillsList()  { return split(technicalSkills, ","); }
    public List<String> getSoftSkillsList()       { return split(softSkills, ","); }
    public List<String> getResponsibilitiesList() { return split(responsibilities, "\\r?\\n"); }
    public List<String> getErrors()               { return errors; }

   
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getProfessionalTitle() { return professionalTitle; }
    public void setProfessionalTitle(String professionalTitle) { this.professionalTitle = professionalTitle; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getProfileUrl() { return profileUrl; }
    public void setProfileUrl(String profileUrl) { this.profileUrl = profileUrl; }

    public String getProfileSummary() { return profileSummary; }
    public void setProfileSummary(String profileSummary) { this.profileSummary = profileSummary; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getGraduationYear() { return graduationYear; }
    public void setGraduationYear(String graduationYear) { this.graduationYear = graduationYear; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }

    public String getTechnicalSkills() { return technicalSkills; }
    public void setTechnicalSkills(String technicalSkills) { this.technicalSkills = technicalSkills; }

    public String getSoftSkills() { return softSkills; }
    public void setSoftSkills(String softSkills) { this.softSkills = softSkills; }
    
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}