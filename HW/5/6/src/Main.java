public class Main
{
    public static void main(String[] args)
    {
        Student a1 = new UndergraduateStudent("Ali", "Nazari", 9631075, "کنکور");
        ((UndergraduateStudent) a1).displayStudentInformation();
        ((UndergraduateStudent) a1).setHowEntered("مهمان");
        System.out.println();
        ((UndergraduateStudent) a1).displayStudentInformation();
        System.out.println("------------------------------");

        Student a2 = new UndergraduateStudent("Baqer", "Abedi", 9531914, "انتقال هیئت علمی دائم");
        ((UndergraduateStudent) a2).displayStudentInformation();
        ((UndergraduateStudent) a2).setFirstName("محمدباقر");
        System.out.println();
        ((UndergraduateStudent) a2).displayStudentInformation();
        System.out.println("------------------------------");

        Student b1 = new GraduateStudent("Amin", "Hediehloo", 9031068, "Afshar", "Harvard");
        ((GraduateStudent) b1).displayStudentInformation();
        ((GraduateStudent) b1).setAdvisor("فرهادیان");
        System.out.println();
        ((GraduateStudent) b1).displayStudentInformation();
        System.out.println("------------------------------");

        Student b2 = new GraduateStudent("Mohsen", "Afshari", 9131078, "MirBoland", "Stanford");
        ((GraduateStudent) b2).displayStudentInformation();
        ((GraduateStudent) b2).setLastUniversity("شریف");
        System.out.println();
        ((GraduateStudent) b2).displayStudentInformation();
        System.out.println("------------------------------");

    }
}
