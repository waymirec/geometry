package io.nuvalence.geometry.util;

public class ExceptionHelper {
    private ExceptionHelper() { }

    public static RuntimeException softenException(Exception e) {
        return checkednessRemover(e);
    }

    @SuppressWarnings("unchecked")
    private static < T extends Exception > T checkednessRemover(Exception e) throws T {
        throw (T) e;
    }
}
