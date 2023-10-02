public class Time {
    /*
     * System.nanoTime() return system current time in nanoseconds
     * timeStarted will hold the time the application started
     */
    public static double timeStarted = System.nanoTime();

    // return time since the application has started in seconds
    public static double getTime() {
        return (System.nanoTime() - timeStarted) * (1E-9);
    }
}
