# Project: BCS Graduation Check

## By: Benjamin Chang


The **BCS** or **Bachelor's of Computer Science** program is 
a 20-month second degree program designed for university
gradautes with a previous bachelor's degree in another
field and are interested in:
- making a career transition into CS or
- combing their own field with CS

My 210 project called the *BCS Graduation Check* is a tool 
I hope UBC students in the BCS program will be able
to use in order to check their graduation requirements. My
current initial goal is to have something that can check
that a BCS student has satisfied all of their degree
requirements based on their coursework but I am envisioning
adding additional functionality such as prerequisite checking
or promotion evaluations perhaps in the near future. 

My reason for creating this tool is because due to the 
nature of this second degree program, there are additional
requirements, a strict course schedule to be followed
and also a specific list of courses that have to be
completed. Students of the BCS program are a more mature
cohort and we each have our own commitments already whether
that be a job, professional activities or even our own families.
And since we are expected to complete a full degree in an 
accelerated timeframe, nothing would be worse than after
20 months of condensed academic study, to find out that
you don't qualify for graduation because of a missing course,
or credits that don't count. 

###User Stories

For an initial phase of this project I envision a user 
to be able to do the following things:
- run a Year 4 promotion check on their coursework to
check whether or not they completed all the necessary
requirements
- run a graduation check on their entire coursework to
check whether or not they completed all the necessary
requirements
- add courses to their list of coursework based on 
what they've completed, not completed or are expecting to complete
- view their entire coursework for the BCS program so far
to plan for future semesters
- save a current course list to file, recording their coursework so far
- load the saved course list from file

###Phase 4: Task 2
I decided to choose the type hierarchy task. What I've done is have my
classes `PromotionCheck` and `GraduatonCheck` extend the JMenuItem class.
Since I wanted these two subclasses to behave almost identically but with
different outputs, I decided to have them extend a class in the Java swing
library but override particular methods to get the functionality I wanted. 
In particular, I overrided the ActionEvent method for the ActionListener
method so that for the promotion check it would run my PromotionCheck method
from the CourseList class and for the graduation check version it would
run my GraduationCheck method. 


###Phase 4: Task 3
Looking at my UML class diagram I'm noticing that many of my classes depend
on the `CoursesField` class. Ideally if I could, I would want to decrease
the coupling in the diagram and lower the number of association arrows pointing
to `CoursesField`. One way I could do this is that my classes `PromotionCheck` and
`GraduationCheck` both need to access the CoursesField, but since in terms of the
user interface the two are accessed from `MenuBar`, I would probably have the two 
Check classes be associated with MenuBar thereby decreasing their coupling with 
`CoursesField` but not losing functionality since they can still access it through
`MenuBar`. 

A bigger change I would make however is that I notice since my `CourseCheckerApp` is 
made of three items `AddCourse`, `MenuBar` and `CoursesField` but almost everything
depends on `CoursesField`, I would create bi-directional associations between 
`CoursesField` and both `AddCourse` and `MenuBar`. That way since these two classes would
now be under `CoursesField`, my `CourseCheckerApp` would only have one association, `CoursesField`.
