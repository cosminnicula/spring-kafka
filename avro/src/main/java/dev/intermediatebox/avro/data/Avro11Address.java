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
public class Avro11Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5459814146307469830L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Avro11Address\",\"namespace\":\"dev.intermediatebox.avro.data\",\"fields\":[{\"name\":\"addressLine1\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"addressLine2\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"city\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"zipCode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"country\",\"type\":{\"type\":\"record\",\"name\":\"Avro11Country\",\"fields\":[{\"name\":\"code\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"ISO 3166-1 alpha-2 country code\"},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"country name\"}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Avro11Address> ENCODER =
      new BinaryMessageEncoder<Avro11Address>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Avro11Address> DECODER =
      new BinaryMessageDecoder<Avro11Address>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Avro11Address> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Avro11Address> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Avro11Address> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Avro11Address>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Avro11Address to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Avro11Address from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Avro11Address instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Avro11Address fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String addressLine1;
  private java.lang.String addressLine2;
  private java.lang.String city;
  private java.lang.String zipCode;
  private dev.intermediatebox.avro.data.Avro11Country country;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Avro11Address() {}

  /**
   * All-args constructor.
   * @param addressLine1 The new value for addressLine1
   * @param addressLine2 The new value for addressLine2
   * @param city The new value for city
   * @param zipCode The new value for zipCode
   * @param country The new value for country
   */
  public Avro11Address(java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String city, java.lang.String zipCode, dev.intermediatebox.avro.data.Avro11Country country) {
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.city = city;
    this.zipCode = zipCode;
    this.country = country;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return addressLine1;
    case 1: return addressLine2;
    case 2: return city;
    case 3: return zipCode;
    case 4: return country;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: addressLine1 = value$ != null ? value$.toString() : null; break;
    case 1: addressLine2 = value$ != null ? value$.toString() : null; break;
    case 2: city = value$ != null ? value$.toString() : null; break;
    case 3: zipCode = value$ != null ? value$.toString() : null; break;
    case 4: country = (dev.intermediatebox.avro.data.Avro11Country)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'addressLine1' field.
   * @return The value of the 'addressLine1' field.
   */
  public java.lang.String getAddressLine1() {
    return addressLine1;
  }


  /**
   * Sets the value of the 'addressLine1' field.
   * @param value the value to set.
   */
  public void setAddressLine1(java.lang.String value) {
    this.addressLine1 = value;
  }

  /**
   * Gets the value of the 'addressLine2' field.
   * @return The value of the 'addressLine2' field.
   */
  public java.lang.String getAddressLine2() {
    return addressLine2;
  }


  /**
   * Sets the value of the 'addressLine2' field.
   * @param value the value to set.
   */
  public void setAddressLine2(java.lang.String value) {
    this.addressLine2 = value;
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
   * Gets the value of the 'zipCode' field.
   * @return The value of the 'zipCode' field.
   */
  public java.lang.String getZipCode() {
    return zipCode;
  }


  /**
   * Sets the value of the 'zipCode' field.
   * @param value the value to set.
   */
  public void setZipCode(java.lang.String value) {
    this.zipCode = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public dev.intermediatebox.avro.data.Avro11Country getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(dev.intermediatebox.avro.data.Avro11Country value) {
    this.country = value;
  }

  /**
   * Creates a new Avro11Address RecordBuilder.
   * @return A new Avro11Address RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro11Address.Builder newBuilder() {
    return new dev.intermediatebox.avro.data.Avro11Address.Builder();
  }

  /**
   * Creates a new Avro11Address RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Avro11Address RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro11Address.Builder newBuilder(dev.intermediatebox.avro.data.Avro11Address.Builder other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Avro11Address.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Avro11Address.Builder(other);
    }
  }

  /**
   * Creates a new Avro11Address RecordBuilder by copying an existing Avro11Address instance.
   * @param other The existing instance to copy.
   * @return A new Avro11Address RecordBuilder
   */
  public static dev.intermediatebox.avro.data.Avro11Address.Builder newBuilder(dev.intermediatebox.avro.data.Avro11Address other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.Avro11Address.Builder();
    } else {
      return new dev.intermediatebox.avro.data.Avro11Address.Builder(other);
    }
  }

  /**
   * RecordBuilder for Avro11Address instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Avro11Address>
    implements org.apache.avro.data.RecordBuilder<Avro11Address> {

    private java.lang.String addressLine1;
    private java.lang.String addressLine2;
    private java.lang.String city;
    private java.lang.String zipCode;
    private dev.intermediatebox.avro.data.Avro11Country country;
    private dev.intermediatebox.avro.data.Avro11Country.Builder countryBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Avro11Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.addressLine1)) {
        this.addressLine1 = data().deepCopy(fields()[0].schema(), other.addressLine1);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.addressLine2)) {
        this.addressLine2 = data().deepCopy(fields()[1].schema(), other.addressLine2);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.zipCode)) {
        this.zipCode = data().deepCopy(fields()[3].schema(), other.zipCode);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.country)) {
        this.country = data().deepCopy(fields()[4].schema(), other.country);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (other.hasCountryBuilder()) {
        this.countryBuilder = dev.intermediatebox.avro.data.Avro11Country.newBuilder(other.getCountryBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Avro11Address instance
     * @param other The existing instance to copy.
     */
    private Builder(dev.intermediatebox.avro.data.Avro11Address other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.addressLine1)) {
        this.addressLine1 = data().deepCopy(fields()[0].schema(), other.addressLine1);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.addressLine2)) {
        this.addressLine2 = data().deepCopy(fields()[1].schema(), other.addressLine2);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.zipCode)) {
        this.zipCode = data().deepCopy(fields()[3].schema(), other.zipCode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.country)) {
        this.country = data().deepCopy(fields()[4].schema(), other.country);
        fieldSetFlags()[4] = true;
      }
      this.countryBuilder = null;
    }

    /**
      * Gets the value of the 'addressLine1' field.
      * @return The value.
      */
    public java.lang.String getAddressLine1() {
      return addressLine1;
    }


    /**
      * Sets the value of the 'addressLine1' field.
      * @param value The value of 'addressLine1'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder setAddressLine1(java.lang.String value) {
      validate(fields()[0], value);
      this.addressLine1 = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'addressLine1' field has been set.
      * @return True if the 'addressLine1' field has been set, false otherwise.
      */
    public boolean hasAddressLine1() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'addressLine1' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder clearAddressLine1() {
      addressLine1 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'addressLine2' field.
      * @return The value.
      */
    public java.lang.String getAddressLine2() {
      return addressLine2;
    }


    /**
      * Sets the value of the 'addressLine2' field.
      * @param value The value of 'addressLine2'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder setAddressLine2(java.lang.String value) {
      validate(fields()[1], value);
      this.addressLine2 = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'addressLine2' field has been set.
      * @return True if the 'addressLine2' field has been set, false otherwise.
      */
    public boolean hasAddressLine2() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'addressLine2' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder clearAddressLine2() {
      addressLine2 = null;
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
    public dev.intermediatebox.avro.data.Avro11Address.Builder setCity(java.lang.String value) {
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
    public dev.intermediatebox.avro.data.Avro11Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'zipCode' field.
      * @return The value.
      */
    public java.lang.String getZipCode() {
      return zipCode;
    }


    /**
      * Sets the value of the 'zipCode' field.
      * @param value The value of 'zipCode'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder setZipCode(java.lang.String value) {
      validate(fields()[3], value);
      this.zipCode = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'zipCode' field has been set.
      * @return True if the 'zipCode' field has been set, false otherwise.
      */
    public boolean hasZipCode() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'zipCode' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder clearZipCode() {
      zipCode = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public dev.intermediatebox.avro.data.Avro11Country getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder setCountry(dev.intermediatebox.avro.data.Avro11Country value) {
      validate(fields()[4], value);
      this.countryBuilder = null;
      this.country = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[4];
    }

    /**
     * Gets the Builder instance for the 'country' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public dev.intermediatebox.avro.data.Avro11Country.Builder getCountryBuilder() {
      if (countryBuilder == null) {
        if (hasCountry()) {
          setCountryBuilder(dev.intermediatebox.avro.data.Avro11Country.newBuilder(country));
        } else {
          setCountryBuilder(dev.intermediatebox.avro.data.Avro11Country.newBuilder());
        }
      }
      return countryBuilder;
    }

    /**
     * Sets the Builder instance for the 'country' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public dev.intermediatebox.avro.data.Avro11Address.Builder setCountryBuilder(dev.intermediatebox.avro.data.Avro11Country.Builder value) {
      clearCountry();
      countryBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'country' field has an active Builder instance
     * @return True if the 'country' field has an active Builder instance
     */
    public boolean hasCountryBuilder() {
      return countryBuilder != null;
    }

    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.Avro11Address.Builder clearCountry() {
      country = null;
      countryBuilder = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Avro11Address build() {
      try {
        Avro11Address record = new Avro11Address();
        record.addressLine1 = fieldSetFlags()[0] ? this.addressLine1 : (java.lang.String) defaultValue(fields()[0]);
        record.addressLine2 = fieldSetFlags()[1] ? this.addressLine2 : (java.lang.String) defaultValue(fields()[1]);
        record.city = fieldSetFlags()[2] ? this.city : (java.lang.String) defaultValue(fields()[2]);
        record.zipCode = fieldSetFlags()[3] ? this.zipCode : (java.lang.String) defaultValue(fields()[3]);
        if (countryBuilder != null) {
          try {
            record.country = this.countryBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("country"));
            throw e;
          }
        } else {
          record.country = fieldSetFlags()[4] ? this.country : (dev.intermediatebox.avro.data.Avro11Country) defaultValue(fields()[4]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Avro11Address>
    WRITER$ = (org.apache.avro.io.DatumWriter<Avro11Address>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Avro11Address>
    READER$ = (org.apache.avro.io.DatumReader<Avro11Address>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.addressLine1);

    if (this.addressLine2 == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.addressLine2);
    }

    out.writeString(this.city);

    out.writeString(this.zipCode);

    this.country.customEncode(out);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.addressLine1 = in.readString();

      if (in.readIndex() != 1) {
        in.readNull();
        this.addressLine2 = null;
      } else {
        this.addressLine2 = in.readString();
      }

      this.city = in.readString();

      this.zipCode = in.readString();

      if (this.country == null) {
        this.country = new dev.intermediatebox.avro.data.Avro11Country();
      }
      this.country.customDecode(in);

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.addressLine1 = in.readString();
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.addressLine2 = null;
          } else {
            this.addressLine2 = in.readString();
          }
          break;

        case 2:
          this.city = in.readString();
          break;

        case 3:
          this.zipCode = in.readString();
          break;

        case 4:
          if (this.country == null) {
            this.country = new dev.intermediatebox.avro.data.Avro11Country();
          }
          this.country.customDecode(in);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










