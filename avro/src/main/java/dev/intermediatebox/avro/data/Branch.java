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

@org.apache.avro.specific.AvroGenerated
public class Branch extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3345620117761744734L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Branch\",\"namespace\":\"dev.intermediatebox.avro.data\",\"fields\":[{\"name\":\"address\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"branchId\",\"type\":\"long\"},{\"name\":\"city\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"country\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Branch> ENCODER =
      new BinaryMessageEncoder<Branch>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Branch> DECODER =
      new BinaryMessageDecoder<Branch>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Branch> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Branch> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Branch> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Branch>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Branch to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Branch from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Branch instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Branch fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String address;
  private long branchId;
  private java.lang.String city;
  private java.lang.String country;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Branch() {}

  /**
   * All-args constructor.
   * @param address The new value for address
   * @param branchId The new value for branchId
   * @param city The new value for city
   * @param country The new value for country
   */
  public Branch(java.lang.String address, java.lang.Long branchId, java.lang.String city, java.lang.String country) {
    this.address = address;
    this.branchId = branchId;
    this.city = city;
    this.country = country;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return address;
    case 1: return branchId;
    case 2: return city;
    case 3: return country;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: address = value$ != null ? value$.toString() : null; break;
    case 1: branchId = (java.lang.Long)value$; break;
    case 2: city = value$ != null ? value$.toString() : null; break;
    case 3: country = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public java.lang.String getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(java.lang.String value) {
    this.address = value;
  }

  /**
   * Gets the value of the 'branchId' field.
   * @return The value of the 'branchId' field.
   */
  public long getBranchId() {
    return branchId;
  }


  /**
   * Sets the value of the 'branchId' field.
   * @param value the value to set.
   */
  public void setBranchId(long value) {
    this.branchId = value;
  }

  /**
   * Gets the value of the 'city' field.
   * @return The value of the 'city' field.
   */
  public java.lang.String getCity() {
    return city;
  }


  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.String value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public java.lang.String getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.String value) {
    this.country = value;
  }

  /**
   * Creates a new Branch RecordBuilder.
   * @return A new Branch RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Branch.Builder newBuilder() {
    return new dev.intermediatebox.avro.data.Branch.Builder();
  }

  /**
   * Creates a new Branch RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Branch RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Branch.Builder newBuilder(dev.intermediatebox.avro.data.Branch.Builder other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Branch.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Branch.Builder(other);
    }
  }

  /**
   * Creates a new Branch RecordBuilder by copying an existing Branch instance.
   * @param other The existing instance to copy.
   * @return A new Branch RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Branch.Builder newBuilder(dev.intermediatebox.avro.data.Branch other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Branch.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Branch.Builder(other);
    }
  }

  /**
   * RecordBuilder for Branch instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Branch>
    implements org.apache.avro.data.RecordBuilder<Branch> {

    private java.lang.String address;
    private long branchId;
    private java.lang.String city;
    private java.lang.String country;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Branch.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.address)) {
        this.address = data().deepCopy(fields()[0].schema(), other.address);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.branchId)) {
        this.branchId = data().deepCopy(fields()[1].schema(), other.branchId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Branch instance
     * @param other The existing instance to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Branch other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.address)) {
        this.address = data().deepCopy(fields()[0].schema(), other.address);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.branchId)) {
        this.branchId = data().deepCopy(fields()[1].schema(), other.branchId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'address' field.
      * @return The value.
      */
    public java.lang.String getAddress() {
      return address;
    }


    /**
      * Sets the value of the 'address' field.
      * @param value The value of 'address'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder setAddress(java.lang.String value) {
      validate(fields()[0], value);
      this.address = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder clearAddress() {
      address = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'branchId' field.
      * @return The value.
      */
    public long getBranchId() {
      return branchId;
    }


    /**
      * Sets the value of the 'branchId' field.
      * @param value The value of 'branchId'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder setBranchId(long value) {
      validate(fields()[1], value);
      this.branchId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'branchId' field has been set.
      * @return True if the 'branchId' field has been set, false otherwise.
      */
    public boolean hasBranchId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'branchId' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder clearBranchId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'city' field.
      * @return The value.
      */
    public java.lang.String getCity() {
      return city;
    }


    /**
      * Sets the value of the 'city' field.
      * @param value The value of 'city'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder setCity(java.lang.String value) {
      validate(fields()[2], value);
      this.city = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder clearCity() {
      city = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public java.lang.String getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder setCountry(java.lang.String value) {
      validate(fields()[3], value);
      this.country = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Branch.Builder clearCountry() {
      country = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Branch build() {
      try {
        Branch record = new Branch();
        record.address = fieldSetFlags()[0] ? this.address : (java.lang.String) defaultValue(fields()[0]);
        record.branchId = fieldSetFlags()[1] ? this.branchId : (java.lang.Long) defaultValue(fields()[1]);
        record.city = fieldSetFlags()[2] ? this.city : (java.lang.String) defaultValue(fields()[2]);
        record.country = fieldSetFlags()[3] ? this.country : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Branch>
    WRITER$ = (org.apache.avro.io.DatumWriter<Branch>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Branch>
    READER$ = (org.apache.avro.io.DatumReader<Branch>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.address);

    out.writeLong(this.branchId);

    out.writeString(this.city);

    out.writeString(this.country);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.address = in.readString();

      this.branchId = in.readLong();

      this.city = in.readString();

      this.country = in.readString();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.address = in.readString();
          break;

        case 1:
          this.branchId = in.readLong();
          break;

        case 2:
          this.city = in.readString();
          break;

        case 3:
          this.country = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










