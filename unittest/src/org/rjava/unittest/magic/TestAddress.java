package org.rjava.unittest.magic;

import org.rjava.restriction.rulesets.RJavaCore;
import org.rjava.unittest.UnitTest;
import org.vmmagic.unboxed.Address;
import org.vmmagic.unboxed.ObjectReference;
import org.vmmagic.unboxed.Offset;

@RJavaCore
public class TestAddress extends UnitTest{
    public static void main(String[] args) {
        start("Address store/load");
        check(TestAddressStoreLoad());
        
        start("Address offset store/load");
        check(TestAddressOffsetStoreLoad());
        
        start("Address plus/minus int");
        check(TestAddressPlusMinusInt());
        
        start("Address isZero");
        check(TestAddressIsZero());
        
        start("Address fromSignedInt");
        check(TestAddressFromSignedInt());
        
        start("Address EQ");
        check(TestAddressEQ());
        
        start("Address zero.EQ(null)");
        check(TestAddressZeroFromNull());
        
        start("Address prepare/attempt int");
        check(TestAddressAttemptInt());
    }

    public static boolean TestAddressStoreLoad() {
        Integer i = new Integer(1);
        Address addr = ObjectReference.fromObject(i).toAddress();
        addr.store(100);
        int load = addr.loadInt();
        
        return load == 100;
    }
    
    public static boolean TestAddressOffsetStoreLoad() {
        Integer[] array = new Integer[5];
        Address addr = ObjectReference.fromObject(array).toAddress();
        
        // test offset store
        Offset offset = Offset.fromIntSignExtend(2);
        addr.store(100, offset);
        Address storeAddress = addr.plus(offset);
        int load = storeAddress.loadInt();
        
        // test offset load
        storeAddress.store(101);
        int load2 = addr.loadInt(offset);
        
        return load == 100 && load2 == 101;        
    }
    
    public static boolean TestAddressPlusMinusInt() {
        Integer i = new Integer(99);
        Address addr = ObjectReference.fromObject(i).toAddress();
        
        addr = addr.plus(1);
        addr = addr.minus(1);
        
        Integer i2 = (Integer) addr.toObjectReference().toObject();
        
        return i2 == 99;
    }
    
    public static boolean TestAddressIsZero() {
        Address addr = Address.zero();
        addr = addr.plus(1);
        addr = addr.minus(1);
        return addr.isZero();
    }
    
    public static boolean TestAddressFromSignedInt() {
        Address addr = Address.fromIntSignExtend(1);
        addr = addr.minus(1);
        
        Address addr2 = Address.fromIntSignExtend(-1);
        addr2 = addr2.plus(1);
        
        return addr.isZero() && addr2.isZero();
    }
    
    public static boolean TestAddressEQ() {
        Integer i = new Integer(1);
        Address addr = ObjectReference.fromObject(i).toAddress();
        Address addr2 = ObjectReference.fromObject(i).toAddress();
        
        return addr.EQ(addr2);
    }
    
    public static boolean TestAddressZeroFromNull() {
        Address zeroAddr = Address.zero();
        Address fromNull = ObjectReference.fromObject(null).toAddress();
        
        return zeroAddr.EQ(fromNull);
    }
    
    public static boolean TestAddressAttemptInt() {
        Integer i = new Integer(1);
        
        Address addr = ObjectReference.fromObject(i).toAddress();
        addr.store(1);
        
        int oldValue, newValue;
        do {
            oldValue = addr.prepareInt();
            newValue = 9;
        } while (!addr.attempt(oldValue, newValue));
        
        return addr.loadInt() == 9;
    }
}
