package com.manishpatole.pocfacts.utility;

import java.util.Collection;
import java.util.Map;

/**
 * Collection Utility.
 *
 * @author manishpatole
 */

public class CollectionUtil {
  private CollectionUtil() {
  }

  /**
   * Checks if collection is empty.
   *
   * @param collection
   * @return
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Checks if collection is not empty.
   *
   * @param collection
   * @return
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}