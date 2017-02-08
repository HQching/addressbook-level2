package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    @Test
    public void isAnyNull() {
        // empty list
        assertFalse(Utils.isAnyNull());

        // non empty list with one non-null argument
        assertFalse(Utils.isAnyNull(new ArrayList<Object>()));
        assertFalse(Utils.isAnyNull(new Object()));
        assertFalse(Utils.isAnyNull("test"));
        assertFalse(Utils.isAnyNull(1));
        assertFalse(Utils.isAnyNull(""));
        
        // non empty list with more than one non-null arguments
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        assertFalse(Utils.isAnyNull("item", "item", "item"));
        assertFalse(Utils.isAnyNull(1,2,3,4,5,6,7,8,9,10));
        assertFalse(Utils.isAnyNull(" ", "\n", "", 23, "string", new Object()));
        
        // non empty list with same objects as arguments
        Object a = new Object();
        Object b = a;
        assertFalse(Utils.isAnyNull(a,b));
        a = null;
        assertFalse(Utils.isAnyNull(b));
        
        // non empty list with just one null at the beginning
        assertTrue(Utils.isAnyNull((Object) null));
        assertTrue(Utils.isAnyNull(null, "", new Object()));
        assertTrue(Utils.isAnyNull(null, new Object(), new Object()));

        // non empty list with nulls in the middle
        assertTrue(Utils.isAnyNull(new Object(), null, null, "test"));
        assertTrue(Utils.isAnyNull("", null, new Object()));

        // non empty list with one null as the last element
        assertTrue(Utils.isAnyNull("", new Object(), null));
        assertTrue(Utils.isAnyNull(new Object(), new Object(), null));

        // non empty list with nulls all over the list
        assertTrue(Utils.isAnyNull("", null, 78, "string", null, new Object(), null));
        
        // confirms nulls inside the list are not considered
        List<Object> nullList = Arrays.asList((Object) null);
        assertFalse(Utils.isAnyNull(nullList));
        assertTrue(Utils.isAnyNull(nullList.get(0)));
        
    }

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);
        assertAreUnique(new Object(), new Object(), new Object());
        assertAreUnique(1,"abc", null, new Object());
        
        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, new Integer(1));
        assertNotUnique(null, 1, new Integer(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
        
        //testing using arrays
        int[] arr1 = { 1, 3, 5, 7, 9};
        int [] arr2 = arr1;
        int [] arr3 = {2, 4, 6, 8, 10};
        assertAreUnique(arr1, arr3);
        assertNotUnique(arr1, arr2);
        arr2[0] = 2;
        assertNotUnique(arr1, arr2);
        arr1 = null;
        assertAreUnique(arr1, arr2);

    }
    

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
