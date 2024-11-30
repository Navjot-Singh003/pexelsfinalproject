package com.pexels.network;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Abstract class ApiResult to represent both success and error results.
public abstract class ApiResult {

   private ApiResult() {}

   // Error subclass representing an error result.
   public static final class Error extends ApiResult {
      @NotNull
      private final Throwable exception;

      // Constructor for the error result with an exception.
      public Error(@NotNull Throwable exception) {
         if (exception == null) throw new IllegalArgumentException("Exception cannot be null");
         this.exception = exception;
      }

      // Getter for the exception.
      @NotNull
      public Throwable getException() {
         return exception;
      }

      // Copy method for Error.
      @NotNull
      public Error copy(@NotNull Throwable exception) {
         return new Error(exception);
      }

      @Override
      public String toString() {
         return "Error(exception=" + exception + ")";
      }

      @Override
      public int hashCode() {
         return exception.hashCode();
      }

      @Override
      public boolean equals(Object other) {
         if (this == other) return true;
         if (other == null || getClass() != other.getClass()) return false;
         Error error = (Error) other;
         return exception.equals(error.exception);
      }
   }

   // Success subclass representing a successful result with data.
   public static final class Success<T> extends ApiResult {
      @NotNull
      private final T data;

      // Constructor for success result with data.
      public Success(@NotNull T data) {
         if (data == null) throw new IllegalArgumentException("Data cannot be null");
         this.data = data;
      }

      // Getter for the data.
      @NotNull
      public T getData() {
         return data;
      }

      // Copy method for Success.
      @NotNull
      public Success<T> copy(@NotNull T data) {
         return new Success<>(data);
      }

      @Override
      public String toString() {
         return "Success(data=" + data + ")";
      }

      @Override
      public int hashCode() {
         return data != null ? data.hashCode() : 0;
      }

      @Override
      public boolean equals(Object other) {
         if (this == other) return true;
         if (other == null || getClass() != other.getClass()) return false;
         Success<?> success = (Success<?>) other;
         return data.equals(success.data);
      }
   }
}
