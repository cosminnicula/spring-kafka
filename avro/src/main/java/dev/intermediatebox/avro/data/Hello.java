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

/** Hello world avro */
@org.apache.avro.specific.AvroGenerated
public class Hello extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7297860734815333360L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Hello\",\"namespace\":\"dev.intermediatebox.avro.data\",\"doc\":\"Hello world avro\",\"fields\":[{\"name\":\"myStringField\",\"type\":\"string\",\"doc\":\"Just a string\"},{\"name\":\"myIntField\",\"type\":\"int\",\"doc\":\"Just an int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Hello> ENCODER =
      new BinaryMessageEncoder<Hello>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Hello> DECODER =
      new BinaryMessageDecoder<Hello>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Hello> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Hello> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Hello> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Hello>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Hello to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Hello from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Hello instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Hello fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Just a string */
  private java.lang.CharSequence myStringField;
  /** Just an int */
  private int myIntField;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Hello() {}

  /**
   * All-args constructor.
   * @param myStringField Just a string
   * @param myIntField Just an int
   */
  public Hello(java.lang.CharSequence myStringField, java.lang.Integer myIntField) {
    this.myStringField = myStringField;
    this.myIntField = myIntField;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return myStringField;
    case 1: return myIntField;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: myStringField = (java.lang.CharSequence)value$; break;
    case 1: myIntField = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'myStringField' field.
   * @return Just a string
   */
  public java.lang.CharSequence getMyStringField() {
    return myStringField;
  }


  /**
   * Sets the value of the 'myStringField' field.
   * Just a string
   * @param value the value to set.
   */
  public void setMyStringField(java.lang.CharSequence value) {
    this.myStringField = value;
  }

  /**
   * Gets the value of the 'myIntField' field.
   * @return Just an int
   */
  public int getMyIntField() {
    return myIntField;
  }


  /**
   * Sets the value of the 'myIntField' field.
   * Just an int
   * @param value the value to set.
   */
  public void setMyIntField(int value) {
    this.myIntField = value;
  }

  /**
   * Creates a new Hello RecordBuilder.
   * @return A new Hello RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Hello.Builder newBuilder() {
    return new dev.intermediatebox.avro.data.Hello.Builder();
  }

  /**
   * Creates a new Hello RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Hello RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Hello.Builder newBuilder(dev.intermediatebox.avro.data.Hello.Builder other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Hello.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Hello.Builder(other);
    }
  }

  /**
   * Creates a new Hello RecordBuilder by copying an existing Hello instance.
   * @param other The existing instance to copy.
   * @return A new Hello RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Hello.Builder newBuilder(dev.intermediatebox.avro.data.Hello other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Hello.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Hello.Builder(other);
    }
  }

  /**
   * RecordBuilder for Hello instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Hello>
    implements org.apache.avro.data.RecordBuilder<Hello> {

    /** Just a string */
    private java.lang.CharSequence myStringField;
    /** Just an int */
    private int myIntField;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Hello.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.myStringField)) {
        this.myStringField = data().deepCopy(fields()[0].schema(), other.myStringField);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.myIntField)) {
        this.myIntField = data().deepCopy(fields()[1].schema(), other.myIntField);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing Hello instance
     * @param other The existing instance to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Hello other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.myStringField)) {
        this.myStringField = data().deepCopy(fields()[0].schema(), other.myStringField);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.myIntField)) {
        this.myIntField = data().deepCopy(fields()[1].schema(), other.myIntField);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'myStringField' field.
      * Just a string
      * @return The value.
      */
    public java.lang.CharSequence getMyStringField() {
      return myStringField;
    }


    /**
      * Sets the value of the 'myStringField' field.
      * Just a string
      * @param value The value of 'myStringField'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Hello.Builder setMyStringField(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.myStringField = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'myStringField' field has been set.
      * Just a string
      * @return True if the 'myStringField' field has been set, false otherwise.
      */
    public boolean hasMyStringField() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'myStringField' field.
      * Just a string
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Hello.Builder clearMyStringField() {
      myStringField = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'myIntField' field.
      * Just an int
      * @return The value.
      */
    public int getMyIntField() {
      return myIntField;
    }


    /**
      * Sets the value of the 'myIntField' field.
      * Just an int
      * @param value The value of 'myIntField'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Hello.Builder setMyIntField(int value) {
      validate(fields()[1], value);
      this.myIntField = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'myIntField' field has been set.
      * Just an int
      * @return True if the 'myIntField' field has been set, false otherwise.
      */
    public boolean hasMyIntField() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'myIntField' field.
      * Just an int
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Hello.Builder clearMyIntField() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Hello build() {
      try {
        Hello record = new Hello();
        record.myStringField = fieldSetFlags()[0] ? this.myStringField : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.myIntField = fieldSetFlags()[1] ? this.myIntField : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Hello>
    WRITER$ = (org.apache.avro.io.DatumWriter<Hello>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Hello>
    READER$ = (org.apache.avro.io.DatumReader<Hello>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.myStringField);

    out.writeInt(this.myIntField);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.myStringField = in.readString(this.myStringField instanceof Utf8 ? (Utf8)this.myStringField : null);

      this.myIntField = in.readInt();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.myStringField = in.readString(this.myStringField instanceof Utf8 ? (Utf8)this.myStringField : null);
          break;

        case 1:
          this.myIntField = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










