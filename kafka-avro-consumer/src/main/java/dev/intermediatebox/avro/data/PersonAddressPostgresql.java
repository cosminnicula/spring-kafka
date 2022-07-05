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
public class PersonAddressPostgresql extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -612892929998322816L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PersonAddressPostgresql\",\"namespace\":\"dev.intermediatebox.avro.data\",\"fields\":[{\"name\":\"person_id\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"id_card_number\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"full_name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"email\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"address_id\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"address\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"city\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"postal_code\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}],\"connect.name\":\"com.course.avro.data.PersonAddressPostgresql\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PersonAddressPostgresql> ENCODER =
      new BinaryMessageEncoder<PersonAddressPostgresql>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PersonAddressPostgresql> DECODER =
      new BinaryMessageDecoder<PersonAddressPostgresql>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PersonAddressPostgresql> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PersonAddressPostgresql> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PersonAddressPostgresql> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PersonAddressPostgresql>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PersonAddressPostgresql to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PersonAddressPostgresql from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PersonAddressPostgresql instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PersonAddressPostgresql fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String person_id;
  private java.lang.String id_card_number;
  private java.lang.String full_name;
  private java.lang.String email;
  private java.lang.String address_id;
  private java.lang.String address;
  private java.lang.String city;
  private java.lang.String postal_code;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PersonAddressPostgresql() {}

  /**
   * All-args constructor.
   * @param person_id The new value for person_id
   * @param id_card_number The new value for id_card_number
   * @param full_name The new value for full_name
   * @param email The new value for email
   * @param address_id The new value for address_id
   * @param address The new value for address
   * @param city The new value for city
   * @param postal_code The new value for postal_code
   */
  public PersonAddressPostgresql(java.lang.String person_id, java.lang.String id_card_number, java.lang.String full_name, java.lang.String email, java.lang.String address_id, java.lang.String address, java.lang.String city, java.lang.String postal_code) {
    this.person_id = person_id;
    this.id_card_number = id_card_number;
    this.full_name = full_name;
    this.email = email;
    this.address_id = address_id;
    this.address = address;
    this.city = city;
    this.postal_code = postal_code;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return person_id;
    case 1: return id_card_number;
    case 2: return full_name;
    case 3: return email;
    case 4: return address_id;
    case 5: return address;
    case 6: return city;
    case 7: return postal_code;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: person_id = value$ != null ? value$.toString() : null; break;
    case 1: id_card_number = value$ != null ? value$.toString() : null; break;
    case 2: full_name = value$ != null ? value$.toString() : null; break;
    case 3: email = value$ != null ? value$.toString() : null; break;
    case 4: address_id = value$ != null ? value$.toString() : null; break;
    case 5: address = value$ != null ? value$.toString() : null; break;
    case 6: city = value$ != null ? value$.toString() : null; break;
    case 7: postal_code = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'person_id' field.
   * @return The value of the 'person_id' field.
   */
  public java.lang.String getPersonId() {
    return person_id;
  }


  /**
   * Sets the value of the 'person_id' field.
   * @param value the value to set.
   */
  public void setPersonId(java.lang.String value) {
    this.person_id = value;
  }

  /**
   * Gets the value of the 'id_card_number' field.
   * @return The value of the 'id_card_number' field.
   */
  public java.lang.String getIdCardNumber() {
    return id_card_number;
  }


  /**
   * Sets the value of the 'id_card_number' field.
   * @param value the value to set.
   */
  public void setIdCardNumber(java.lang.String value) {
    this.id_card_number = value;
  }

  /**
   * Gets the value of the 'full_name' field.
   * @return The value of the 'full_name' field.
   */
  public java.lang.String getFullName() {
    return full_name;
  }


  /**
   * Sets the value of the 'full_name' field.
   * @param value the value to set.
   */
  public void setFullName(java.lang.String value) {
    this.full_name = value;
  }

  /**
   * Gets the value of the 'email' field.
   * @return The value of the 'email' field.
   */
  public java.lang.String getEmail() {
    return email;
  }


  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(java.lang.String value) {
    this.email = value;
  }

  /**
   * Gets the value of the 'address_id' field.
   * @return The value of the 'address_id' field.
   */
  public java.lang.String getAddressId() {
    return address_id;
  }


  /**
   * Sets the value of the 'address_id' field.
   * @param value the value to set.
   */
  public void setAddressId(java.lang.String value) {
    this.address_id = value;
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
   * Gets the value of the 'postal_code' field.
   * @return The value of the 'postal_code' field.
   */
  public java.lang.String getPostalCode() {
    return postal_code;
  }


  /**
   * Sets the value of the 'postal_code' field.
   * @param value the value to set.
   */
  public void setPostalCode(java.lang.String value) {
    this.postal_code = value;
  }

  /**
   * Creates a new PersonAddressPostgresql RecordBuilder.
   * @return A new PersonAddressPostgresql RecordBuilder
   */
  public static dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder newBuilder() {
    return new dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder();
  }

  /**
   * Creates a new PersonAddressPostgresql RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PersonAddressPostgresql RecordBuilder
   */
  public static dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder newBuilder(dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder();
    } else {
      return new dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder(other);
    }
  }

  /**
   * Creates a new PersonAddressPostgresql RecordBuilder by copying an existing PersonAddressPostgresql instance.
   * @param other The existing instance to copy.
   * @return A new PersonAddressPostgresql RecordBuilder
   */
  public static dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder newBuilder(dev.intermediatebox.avro.data.PersonAddressPostgresql other) {
    if (other == null) {
      return new dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder();
    } else {
      return new dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder(other);
    }
  }

  /**
   * RecordBuilder for PersonAddressPostgresql instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PersonAddressPostgresql>
    implements org.apache.avro.data.RecordBuilder<PersonAddressPostgresql> {

    private java.lang.String person_id;
    private java.lang.String id_card_number;
    private java.lang.String full_name;
    private java.lang.String email;
    private java.lang.String address_id;
    private java.lang.String address;
    private java.lang.String city;
    private java.lang.String postal_code;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.person_id)) {
        this.person_id = data().deepCopy(fields()[0].schema(), other.person_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.id_card_number)) {
        this.id_card_number = data().deepCopy(fields()[1].schema(), other.id_card_number);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.full_name)) {
        this.full_name = data().deepCopy(fields()[2].schema(), other.full_name);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.email)) {
        this.email = data().deepCopy(fields()[3].schema(), other.email);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.address_id)) {
        this.address_id = data().deepCopy(fields()[4].schema(), other.address_id);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.address)) {
        this.address = data().deepCopy(fields()[5].schema(), other.address);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.city)) {
        this.city = data().deepCopy(fields()[6].schema(), other.city);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.postal_code)) {
        this.postal_code = data().deepCopy(fields()[7].schema(), other.postal_code);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing PersonAddressPostgresql instance
     * @param other The existing instance to copy.
     */
    private Builder(dev.intermediatebox.avro.data.PersonAddressPostgresql other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.person_id)) {
        this.person_id = data().deepCopy(fields()[0].schema(), other.person_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id_card_number)) {
        this.id_card_number = data().deepCopy(fields()[1].schema(), other.id_card_number);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.full_name)) {
        this.full_name = data().deepCopy(fields()[2].schema(), other.full_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.email)) {
        this.email = data().deepCopy(fields()[3].schema(), other.email);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.address_id)) {
        this.address_id = data().deepCopy(fields()[4].schema(), other.address_id);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.address)) {
        this.address = data().deepCopy(fields()[5].schema(), other.address);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.city)) {
        this.city = data().deepCopy(fields()[6].schema(), other.city);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.postal_code)) {
        this.postal_code = data().deepCopy(fields()[7].schema(), other.postal_code);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'person_id' field.
      * @return The value.
      */
    public java.lang.String getPersonId() {
      return person_id;
    }


    /**
      * Sets the value of the 'person_id' field.
      * @param value The value of 'person_id'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setPersonId(java.lang.String value) {
      validate(fields()[0], value);
      this.person_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'person_id' field has been set.
      * @return True if the 'person_id' field has been set, false otherwise.
      */
    public boolean hasPersonId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'person_id' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearPersonId() {
      person_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'id_card_number' field.
      * @return The value.
      */
    public java.lang.String getIdCardNumber() {
      return id_card_number;
    }


    /**
      * Sets the value of the 'id_card_number' field.
      * @param value The value of 'id_card_number'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setIdCardNumber(java.lang.String value) {
      validate(fields()[1], value);
      this.id_card_number = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'id_card_number' field has been set.
      * @return True if the 'id_card_number' field has been set, false otherwise.
      */
    public boolean hasIdCardNumber() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'id_card_number' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearIdCardNumber() {
      id_card_number = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'full_name' field.
      * @return The value.
      */
    public java.lang.String getFullName() {
      return full_name;
    }


    /**
      * Sets the value of the 'full_name' field.
      * @param value The value of 'full_name'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setFullName(java.lang.String value) {
      validate(fields()[2], value);
      this.full_name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'full_name' field has been set.
      * @return True if the 'full_name' field has been set, false otherwise.
      */
    public boolean hasFullName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'full_name' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearFullName() {
      full_name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'email' field.
      * @return The value.
      */
    public java.lang.String getEmail() {
      return email;
    }


    /**
      * Sets the value of the 'email' field.
      * @param value The value of 'email'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setEmail(java.lang.String value) {
      validate(fields()[3], value);
      this.email = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'email' field has been set.
      * @return True if the 'email' field has been set, false otherwise.
      */
    public boolean hasEmail() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'email' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearEmail() {
      email = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'address_id' field.
      * @return The value.
      */
    public java.lang.String getAddressId() {
      return address_id;
    }


    /**
      * Sets the value of the 'address_id' field.
      * @param value The value of 'address_id'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setAddressId(java.lang.String value) {
      validate(fields()[4], value);
      this.address_id = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'address_id' field has been set.
      * @return True if the 'address_id' field has been set, false otherwise.
      */
    public boolean hasAddressId() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'address_id' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearAddressId() {
      address_id = null;
      fieldSetFlags()[4] = false;
      return this;
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
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setAddress(java.lang.String value) {
      validate(fields()[5], value);
      this.address = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearAddress() {
      address = null;
      fieldSetFlags()[5] = false;
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
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setCity(java.lang.String value) {
      validate(fields()[6], value);
      this.city = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearCity() {
      city = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'postal_code' field.
      * @return The value.
      */
    public java.lang.String getPostalCode() {
      return postal_code;
    }


    /**
      * Sets the value of the 'postal_code' field.
      * @param value The value of 'postal_code'.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder setPostalCode(java.lang.String value) {
      validate(fields()[7], value);
      this.postal_code = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'postal_code' field has been set.
      * @return True if the 'postal_code' field has been set, false otherwise.
      */
    public boolean hasPostalCode() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'postal_code' field.
      * @return This builder.
      */
    public dev.intermediatebox.avro.data.PersonAddressPostgresql.Builder clearPostalCode() {
      postal_code = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PersonAddressPostgresql build() {
      try {
        PersonAddressPostgresql record = new PersonAddressPostgresql();
        record.person_id = fieldSetFlags()[0] ? this.person_id : (java.lang.String) defaultValue(fields()[0]);
        record.id_card_number = fieldSetFlags()[1] ? this.id_card_number : (java.lang.String) defaultValue(fields()[1]);
        record.full_name = fieldSetFlags()[2] ? this.full_name : (java.lang.String) defaultValue(fields()[2]);
        record.email = fieldSetFlags()[3] ? this.email : (java.lang.String) defaultValue(fields()[3]);
        record.address_id = fieldSetFlags()[4] ? this.address_id : (java.lang.String) defaultValue(fields()[4]);
        record.address = fieldSetFlags()[5] ? this.address : (java.lang.String) defaultValue(fields()[5]);
        record.city = fieldSetFlags()[6] ? this.city : (java.lang.String) defaultValue(fields()[6]);
        record.postal_code = fieldSetFlags()[7] ? this.postal_code : (java.lang.String) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PersonAddressPostgresql>
    WRITER$ = (org.apache.avro.io.DatumWriter<PersonAddressPostgresql>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PersonAddressPostgresql>
    READER$ = (org.apache.avro.io.DatumReader<PersonAddressPostgresql>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.person_id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.person_id);
    }

    if (this.id_card_number == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.id_card_number);
    }

    if (this.full_name == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.full_name);
    }

    if (this.email == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.email);
    }

    if (this.address_id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.address_id);
    }

    if (this.address == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.address);
    }

    if (this.city == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.city);
    }

    if (this.postal_code == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.postal_code);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.person_id = null;
      } else {
        this.person_id = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.id_card_number = null;
      } else {
        this.id_card_number = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.full_name = null;
      } else {
        this.full_name = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.email = null;
      } else {
        this.email = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.address_id = null;
      } else {
        this.address_id = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.address = null;
      } else {
        this.address = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.city = null;
      } else {
        this.city = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.postal_code = null;
      } else {
        this.postal_code = in.readString();
      }

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.person_id = null;
          } else {
            this.person_id = in.readString();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.id_card_number = null;
          } else {
            this.id_card_number = in.readString();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.full_name = null;
          } else {
            this.full_name = in.readString();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.email = null;
          } else {
            this.email = in.readString();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.address_id = null;
          } else {
            this.address_id = in.readString();
          }
          break;

        case 5:
          if (in.readIndex() != 1) {
            in.readNull();
            this.address = null;
          } else {
            this.address = in.readString();
          }
          break;

        case 6:
          if (in.readIndex() != 1) {
            in.readNull();
            this.city = null;
          } else {
            this.city = in.readString();
          }
          break;

        case 7:
          if (in.readIndex() != 1) {
            in.readNull();
            this.postal_code = null;
          } else {
            this.postal_code = in.readString();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










