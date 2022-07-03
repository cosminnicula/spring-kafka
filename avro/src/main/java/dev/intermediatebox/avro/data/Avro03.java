/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package dev.intermediatebox.avro.data;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Sample optional using union */
@org.apache.avro.specific.AvroGenerated
public class Avro03 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5617004717505695405L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Avro03\",\"namespace\":\"dev.intermediatebox.avro.data\",\"doc\":\"Sample optional using union\",\"fields\":[{\"name\":\"myMandatoryValue\",\"type\":\"string\"},{\"name\":\"myOptionalValue\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"myOptionalValueWithDefault\",\"type\":[\"string\",\"null\"],\"default\":\"The default value\"},{\"name\":\"myWeirdButPossibleValue\",\"type\":[\"int\",\"boolean\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Avro03> ENCODER =
      new BinaryMessageEncoder<Avro03>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Avro03> DECODER =
      new BinaryMessageDecoder<Avro03>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Avro03> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Avro03> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Avro03> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Avro03>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Avro03 to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Avro03 from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Avro03 instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Avro03 fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence myMandatoryValue;
  private java.lang.CharSequence myOptionalValue;
  private java.lang.CharSequence myOptionalValueWithDefault;
  private java.lang.Object myWeirdButPossibleValue;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Avro03() {}

  /**
   * All-args constructor.
   * @param myMandatoryValue The new value for myMandatoryValue
   * @param myOptionalValue The new value for myOptionalValue
   * @param myOptionalValueWithDefault The new value for myOptionalValueWithDefault
   * @param myWeirdButPossibleValue The new value for myWeirdButPossibleValue
   */
  public Avro03(java.lang.CharSequence myMandatoryValue, java.lang.CharSequence myOptionalValue, java.lang.CharSequence myOptionalValueWithDefault, java.lang.Object myWeirdButPossibleValue) {
    this.myMandatoryValue = myMandatoryValue;
    this.myOptionalValue = myOptionalValue;
    this.myOptionalValueWithDefault = myOptionalValueWithDefault;
    this.myWeirdButPossibleValue = myWeirdButPossibleValue;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return myMandatoryValue;
    case 1: return myOptionalValue;
    case 2: return myOptionalValueWithDefault;
    case 3: return myWeirdButPossibleValue;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: myMandatoryValue = (java.lang.CharSequence)value$; break;
    case 1: myOptionalValue = (java.lang.CharSequence)value$; break;
    case 2: myOptionalValueWithDefault = (java.lang.CharSequence)value$; break;
    case 3: myWeirdButPossibleValue = value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'myMandatoryValue' field.
   * @return The value of the 'myMandatoryValue' field.
   */
  public java.lang.CharSequence getMyMandatoryValue() {
    return myMandatoryValue;
  }


  /**
   * Sets the value of the 'myMandatoryValue' field.
   * @param value the value to set.
   */
  public void setMyMandatoryValue(java.lang.CharSequence value) {
    this.myMandatoryValue = value;
  }

  /**
   * Gets the value of the 'myOptionalValue' field.
   * @return The value of the 'myOptionalValue' field.
   */
  public java.lang.CharSequence getMyOptionalValue() {
    return myOptionalValue;
  }


  /**
   * Sets the value of the 'myOptionalValue' field.
   * @param value the value to set.
   */
  public void setMyOptionalValue(java.lang.CharSequence value) {
    this.myOptionalValue = value;
  }

  /**
   * Gets the value of the 'myOptionalValueWithDefault' field.
   * @return The value of the 'myOptionalValueWithDefault' field.
   */
  public java.lang.CharSequence getMyOptionalValueWithDefault() {
    return myOptionalValueWithDefault;
  }


  /**
   * Sets the value of the 'myOptionalValueWithDefault' field.
   * @param value the value to set.
   */
  public void setMyOptionalValueWithDefault(java.lang.CharSequence value) {
    this.myOptionalValueWithDefault = value;
  }

  /**
   * Gets the value of the 'myWeirdButPossibleValue' field.
   * @return The value of the 'myWeirdButPossibleValue' field.
   */
  public java.lang.Object getMyWeirdButPossibleValue() {
    return myWeirdButPossibleValue;
  }


  /**
   * Sets the value of the 'myWeirdButPossibleValue' field.
   * @param value the value to set.
   */
  public void setMyWeirdButPossibleValue(java.lang.Object value) {
    this.myWeirdButPossibleValue = value;
  }

  /**
   * Creates a new Avro03 RecordBuilder.
   * @return A new Avro03 RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro03.Builder newBuilder() {
    return new dev.intermediatebox.avro.data.Avro03.Builder();
  }

  /**
   * Creates a new Avro03 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Avro03 RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro03.Builder newBuilder(dev.intermediatebox.avro.data.Avro03.Builder other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Avro03.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Avro03.Builder(other);
    }
  }

  /**
   * Creates a new Avro03 RecordBuilder by copying an existing Avro03 instance.
   * @param other The existing instance to copy.
   * @return A new Avro03 RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro03.Builder newBuilder(dev.intermediatebox.avro.data.Avro03 other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Avro03.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Avro03.Builder(other);
    }
  }

  /**
   * RecordBuilder for Avro03 instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Avro03>
    implements org.apache.avro.data.RecordBuilder<Avro03> {

    private java.lang.CharSequence myMandatoryValue;
    private java.lang.CharSequence myOptionalValue;
    private java.lang.CharSequence myOptionalValueWithDefault;
    private java.lang.Object myWeirdButPossibleValue;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Avro03.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.myMandatoryValue)) {
        this.myMandatoryValue = data().deepCopy(fields()[0].schema(), other.myMandatoryValue);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.myOptionalValue)) {
        this.myOptionalValue = data().deepCopy(fields()[1].schema(), other.myOptionalValue);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.myOptionalValueWithDefault)) {
        this.myOptionalValueWithDefault = data().deepCopy(fields()[2].schema(), other.myOptionalValueWithDefault);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.myWeirdButPossibleValue)) {
        this.myWeirdButPossibleValue = data().deepCopy(fields()[3].schema(), other.myWeirdButPossibleValue);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Avro03 instance
     * @param other The existing instance to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Avro03 other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.myMandatoryValue)) {
        this.myMandatoryValue = data().deepCopy(fields()[0].schema(), other.myMandatoryValue);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.myOptionalValue)) {
        this.myOptionalValue = data().deepCopy(fields()[1].schema(), other.myOptionalValue);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.myOptionalValueWithDefault)) {
        this.myOptionalValueWithDefault = data().deepCopy(fields()[2].schema(), other.myOptionalValueWithDefault);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.myWeirdButPossibleValue)) {
        this.myWeirdButPossibleValue = data().deepCopy(fields()[3].schema(), other.myWeirdButPossibleValue);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'myMandatoryValue' field.
      * @return The value.
      */
    public java.lang.CharSequence getMyMandatoryValue() {
      return myMandatoryValue;
    }


    /**
      * Sets the value of the 'myMandatoryValue' field.
      * @param value The value of 'myMandatoryValue'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder setMyMandatoryValue(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.myMandatoryValue = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'myMandatoryValue' field has been set.
      * @return True if the 'myMandatoryValue' field has been set, false otherwise.
      */
    public boolean hasMyMandatoryValue() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'myMandatoryValue' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder clearMyMandatoryValue() {
      myMandatoryValue = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'myOptionalValue' field.
      * @return The value.
      */
    public java.lang.CharSequence getMyOptionalValue() {
      return myOptionalValue;
    }


    /**
      * Sets the value of the 'myOptionalValue' field.
      * @param value The value of 'myOptionalValue'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder setMyOptionalValue(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.myOptionalValue = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'myOptionalValue' field has been set.
      * @return True if the 'myOptionalValue' field has been set, false otherwise.
      */
    public boolean hasMyOptionalValue() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'myOptionalValue' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder clearMyOptionalValue() {
      myOptionalValue = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'myOptionalValueWithDefault' field.
      * @return The value.
      */
    public java.lang.CharSequence getMyOptionalValueWithDefault() {
      return myOptionalValueWithDefault;
    }


    /**
      * Sets the value of the 'myOptionalValueWithDefault' field.
      * @param value The value of 'myOptionalValueWithDefault'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder setMyOptionalValueWithDefault(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.myOptionalValueWithDefault = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'myOptionalValueWithDefault' field has been set.
      * @return True if the 'myOptionalValueWithDefault' field has been set, false otherwise.
      */
    public boolean hasMyOptionalValueWithDefault() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'myOptionalValueWithDefault' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder clearMyOptionalValueWithDefault() {
      myOptionalValueWithDefault = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'myWeirdButPossibleValue' field.
      * @return The value.
      */
    public java.lang.Object getMyWeirdButPossibleValue() {
      return myWeirdButPossibleValue;
    }


    /**
      * Sets the value of the 'myWeirdButPossibleValue' field.
      * @param value The value of 'myWeirdButPossibleValue'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder setMyWeirdButPossibleValue(java.lang.Object value) {
      validate(fields()[3], value);
      this.myWeirdButPossibleValue = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'myWeirdButPossibleValue' field has been set.
      * @return True if the 'myWeirdButPossibleValue' field has been set, false otherwise.
      */
    public boolean hasMyWeirdButPossibleValue() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'myWeirdButPossibleValue' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro03.Builder clearMyWeirdButPossibleValue() {
      myWeirdButPossibleValue = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Avro03 build() {
      try {
        Avro03 record = new Avro03();
        record.myMandatoryValue = fieldSetFlags()[0] ? this.myMandatoryValue : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.myOptionalValue = fieldSetFlags()[1] ? this.myOptionalValue : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.myOptionalValueWithDefault = fieldSetFlags()[2] ? this.myOptionalValueWithDefault : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.myWeirdButPossibleValue = fieldSetFlags()[3] ? this.myWeirdButPossibleValue :  defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Avro03>
    WRITER$ = (org.apache.avro.io.DatumWriter<Avro03>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Avro03>
    READER$ = (org.apache.avro.io.DatumReader<Avro03>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










