package cz.kul.prime.cli;

/**
 * Parameters the application is executed with.
 */
public class ApplicationParams {

    private String fileName;
    private boolean briefOutput;
    private String detector;

    public ApplicationParams(String fileName, boolean briefOutput, String detector) {
        this.fileName = fileName;
        this.briefOutput = briefOutput;
        this.detector = detector;
    }

    /**
     * @return path to the file which contains numbers
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * If true the application prints prime numbers only, no other messages.
     * 
     * @return true if the brief output is on
     */
    public boolean isBriefOutput() {
        return briefOutput;
    }

    /**
     * @return name of the detector implementation
     */
    public String getDetector() {
        return detector;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (briefOutput ? 1231 : 1237);
        result = prime * result + ((detector == null) ? 0 : detector.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ApplicationParams other = (ApplicationParams) obj;
        if (briefOutput != other.briefOutput) return false;
        if (detector == null) {
            if (other.detector != null) return false;
        } else if (!detector.equals(other.detector)) return false;
        if (fileName == null) {
            if (other.fileName != null) return false;
        } else if (!fileName.equals(other.fileName)) return false;
        return true;
    }

}