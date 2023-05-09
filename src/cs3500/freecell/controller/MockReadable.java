package cs3500.freecell.controller;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Fake readable class that always throws IOException.
 * Used for testing controller.
 */
public class MockReadable implements Readable {

  /**
   * Fake read method that throws an IOException.
   * @param cb irrelevant parameter.
   * @return Readable never returns a value.
   * @throws IOException Readable always throws an IOException.
   */
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
