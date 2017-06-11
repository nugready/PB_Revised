package com.professionalbuddies.pb_revised;

/**
 * Created by danie on 5/26/2017.
 */

public class UserInformation {
    public String name;
    public String profession;
    public String university;
    public String education;
    public String age;

    public UserInformation() {
        name = "";
        profession = "";
        university = "";
        education = "";
        age = "";
    }

    public UserInformation(String name, String profession, String university, String education, String age) {
        this.name = name;
        this.profession = profession;
        this.university = university;
        this.education = education;
        this.age = age;
    }

    public String getUserName()
    {
        return name;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getUniversity()
    {
        return university;
    }

    public String getEducation()
    {
        return education;
    }

    public String getAge()
    {
        return age;
    }

}
