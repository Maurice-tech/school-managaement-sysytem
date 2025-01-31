package org.example.models;

import lombok.*;
import org.example.enums.CATEGORY;
import org.example.enums.ROLE;
import org.example.exceptions.UnauthorizedException;
import org.example.service.AdminInterface;

import java.util.HashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends BaseClass implements AdminInterface {
    private String staffRegNo;
    private CATEGORY category;

    @Override
    public ReportSheet admissionProcessing(Applicant applicant, double cutOffMark) {
        School school = new School();

        ReportSheet reportSheet = new ReportSheet();
                    reportSheet.setName(applicant.getName());
                    reportSheet.setSubjectsSatFor(new HashMap<>());
                    reportSheet.setGrade(applicant.getGrade());
                    reportSheet.setAverageScore(applicant.getAverageEntranceScore());
        //1.
        if(this.getRole()!=ROLE.ADMIN_OFFICER && this.getRole()!=ROLE.PRINCIPAL) {
            throw new UnauthorizedException("You don't have this right");
        }

        //2.
        double applicantsAverageScore = applicant.getAverageEntranceScore();
        if(applicantsAverageScore < cutOffMark) {
            reportSheet.setTeachersRemark("Sorry you did not make it, try again latter");
            reportSheet.setStudentLevel("");
        }
            reportSheet.setTeachersRemark("Congratulations!!! You have been place in "+ "Glade ...");
            reportSheet.setStudentLevel("");


        return reportSheet;
    }

    //Implementation steps
//    1. Check if the staff has the right to admit the student
//    2. Applicant should have an average score of 50%
//    3. If not successful report sheet stating so should be created and all performance record
//    4. Placed in a (Added to the class list) class by being offered admission on his/her report sheet and added to the s school student list
//    5. Subjects being allocated to the student

}
