// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SubscribeReq.proto

package com.viagra.chapter08.protobuf;

public final class SubscribeReqProto {
  private SubscribeReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SubcribeReqOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SubcribeReq)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 subReqID = 1;</code>
     */
    int getSubReqID();

    /**
     * <code>string userName = 2;</code>
     */
    String getUserName();
    /**
     * <code>string userName = 2;</code>
     */
    com.google.protobuf.ByteString
        getUserNameBytes();

    /**
     * <code>string productName = 3;</code>
     */
    String getProductName();
    /**
     * <code>string productName = 3;</code>
     */
    com.google.protobuf.ByteString
        getProductNameBytes();

    /**
     * <code>repeated string address = 4;</code>
     */
    java.util.List<String>
        getAddressList();
    /**
     * <code>repeated string address = 4;</code>
     */
    int getAddressCount();
    /**
     * <code>repeated string address = 4;</code>
     */
    String getAddress(int index);
    /**
     * <code>repeated string address = 4;</code>
     */
    com.google.protobuf.ByteString
        getAddressBytes(int index);
  }
  /**
   * Protobuf type {@code SubcribeReq}
   */
  public  static final class SubcribeReq extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SubcribeReq)
      SubcribeReqOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use SubcribeReq.newBuilder() to construct.
    private SubcribeReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SubcribeReq() {
      userName_ = "";
      productName_ = "";
      address_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new SubcribeReq();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SubcribeReq(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              subReqID_ = input.readInt32();
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              userName_ = s;
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              productName_ = s;
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                address_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000001;
              }
              address_.add(s);
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          address_ = address_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SubscribeReqProto.internal_static_SubcribeReq_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SubscribeReqProto.internal_static_SubcribeReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SubscribeReqProto.SubcribeReq.class, SubscribeReqProto.SubcribeReq.Builder.class);
    }

    public static final int SUBREQID_FIELD_NUMBER = 1;
    private int subReqID_;
    /**
     * <code>int32 subReqID = 1;</code>
     */
    public int getSubReqID() {
      return subReqID_;
    }

    public static final int USERNAME_FIELD_NUMBER = 2;
    private volatile Object userName_;
    /**
     * <code>string userName = 2;</code>
     */
    public String getUserName() {
      Object ref = userName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        userName_ = s;
        return s;
      }
    }
    /**
     * <code>string userName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      Object ref = userName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PRODUCTNAME_FIELD_NUMBER = 3;
    private volatile Object productName_;
    /**
     * <code>string productName = 3;</code>
     */
    public String getProductName() {
      Object ref = productName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        productName_ = s;
        return s;
      }
    }
    /**
     * <code>string productName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getProductNameBytes() {
      Object ref = productName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        productName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ADDRESS_FIELD_NUMBER = 4;
    private com.google.protobuf.LazyStringList address_;
    /**
     * <code>repeated string address = 4;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getAddressList() {
      return address_;
    }
    /**
     * <code>repeated string address = 4;</code>
     */
    public int getAddressCount() {
      return address_.size();
    }
    /**
     * <code>repeated string address = 4;</code>
     */
    public String getAddress(int index) {
      return address_.get(index);
    }
    /**
     * <code>repeated string address = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAddressBytes(int index) {
      return address_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (subReqID_ != 0) {
        output.writeInt32(1, subReqID_);
      }
      if (!getUserNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, userName_);
      }
      if (!getProductNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, productName_);
      }
      for (int i = 0; i < address_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, address_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (subReqID_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, subReqID_);
      }
      if (!getUserNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, userName_);
      }
      if (!getProductNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, productName_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < address_.size(); i++) {
          dataSize += computeStringSizeNoTag(address_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getAddressList().size();
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof SubscribeReqProto.SubcribeReq)) {
        return super.equals(obj);
      }
      SubscribeReqProto.SubcribeReq other = (SubscribeReqProto.SubcribeReq) obj;

      if (getSubReqID()
          != other.getSubReqID()) return false;
      if (!getUserName()
          .equals(other.getUserName())) return false;
      if (!getProductName()
          .equals(other.getProductName())) return false;
      if (!getAddressList()
          .equals(other.getAddressList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + SUBREQID_FIELD_NUMBER;
      hash = (53 * hash) + getSubReqID();
      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getUserName().hashCode();
      hash = (37 * hash) + PRODUCTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getProductName().hashCode();
      if (getAddressCount() > 0) {
        hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
        hash = (53 * hash) + getAddressList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static SubscribeReqProto.SubcribeReq parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static SubscribeReqProto.SubcribeReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static SubscribeReqProto.SubcribeReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SubscribeReqProto.SubcribeReq parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(SubscribeReqProto.SubcribeReq prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SubcribeReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SubcribeReq)
        SubscribeReqProto.SubcribeReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return SubscribeReqProto.internal_static_SubcribeReq_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return SubscribeReqProto.internal_static_SubcribeReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                SubscribeReqProto.SubcribeReq.class, SubscribeReqProto.SubcribeReq.Builder.class);
      }

      // Construct using com.viagra.chapter08.protobuf.SubscribeReqProto.SubcribeReq.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        subReqID_ = 0;

        userName_ = "";

        productName_ = "";

        address_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return SubscribeReqProto.internal_static_SubcribeReq_descriptor;
      }

      @Override
      public SubscribeReqProto.SubcribeReq getDefaultInstanceForType() {
        return SubscribeReqProto.SubcribeReq.getDefaultInstance();
      }

      @Override
      public SubscribeReqProto.SubcribeReq build() {
        SubscribeReqProto.SubcribeReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public SubscribeReqProto.SubcribeReq buildPartial() {
        SubscribeReqProto.SubcribeReq result = new SubscribeReqProto.SubcribeReq(this);
        int from_bitField0_ = bitField0_;
        result.subReqID_ = subReqID_;
        result.userName_ = userName_;
        result.productName_ = productName_;
        if (((bitField0_ & 0x00000001) != 0)) {
          address_ = address_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.address_ = address_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof SubscribeReqProto.SubcribeReq) {
          return mergeFrom((SubscribeReqProto.SubcribeReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(SubscribeReqProto.SubcribeReq other) {
        if (other == SubscribeReqProto.SubcribeReq.getDefaultInstance()) return this;
        if (other.getSubReqID() != 0) {
          setSubReqID(other.getSubReqID());
        }
        if (!other.getUserName().isEmpty()) {
          userName_ = other.userName_;
          onChanged();
        }
        if (!other.getProductName().isEmpty()) {
          productName_ = other.productName_;
          onChanged();
        }
        if (!other.address_.isEmpty()) {
          if (address_.isEmpty()) {
            address_ = other.address_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAddressIsMutable();
            address_.addAll(other.address_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        SubscribeReqProto.SubcribeReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (SubscribeReqProto.SubcribeReq) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int subReqID_ ;
      /**
       * <code>int32 subReqID = 1;</code>
       */
      public int getSubReqID() {
        return subReqID_;
      }
      /**
       * <code>int32 subReqID = 1;</code>
       */
      public Builder setSubReqID(int value) {

        subReqID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 subReqID = 1;</code>
       */
      public Builder clearSubReqID() {

        subReqID_ = 0;
        onChanged();
        return this;
      }

      private Object userName_ = "";
      /**
       * <code>string userName = 2;</code>
       */
      public String getUserName() {
        Object ref = userName_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          userName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string userName = 2;</code>
       */
      public com.google.protobuf.ByteString
          getUserNameBytes() {
        Object ref = userName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          userName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string userName = 2;</code>
       */
      public Builder setUserName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        userName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string userName = 2;</code>
       */
      public Builder clearUserName() {

        userName_ = getDefaultInstance().getUserName();
        onChanged();
        return this;
      }
      /**
       * <code>string userName = 2;</code>
       */
      public Builder setUserNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        userName_ = value;
        onChanged();
        return this;
      }

      private Object productName_ = "";
      /**
       * <code>string productName = 3;</code>
       */
      public String getProductName() {
        Object ref = productName_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          productName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string productName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getProductNameBytes() {
        Object ref = productName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          productName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string productName = 3;</code>
       */
      public Builder setProductName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        productName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string productName = 3;</code>
       */
      public Builder clearProductName() {

        productName_ = getDefaultInstance().getProductName();
        onChanged();
        return this;
      }
      /**
       * <code>string productName = 3;</code>
       */
      public Builder setProductNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        productName_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList address_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureAddressIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          address_ = new com.google.protobuf.LazyStringArrayList(address_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getAddressList() {
        return address_.getUnmodifiableView();
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public int getAddressCount() {
        return address_.size();
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public String getAddress(int index) {
        return address_.get(index);
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public com.google.protobuf.ByteString
          getAddressBytes(int index) {
        return address_.getByteString(index);
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public Builder setAddress(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureAddressIsMutable();
        address_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public Builder addAddress(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureAddressIsMutable();
        address_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public Builder addAllAddress(
          Iterable<String> values) {
        ensureAddressIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, address_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public Builder clearAddress() {
        address_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string address = 4;</code>
       */
      public Builder addAddressBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureAddressIsMutable();
        address_.add(value);
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:SubcribeReq)
    }

    // @@protoc_insertion_point(class_scope:SubcribeReq)
    private static final SubscribeReqProto.SubcribeReq DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new SubscribeReqProto.SubcribeReq();
    }

    public static SubscribeReqProto.SubcribeReq getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SubcribeReq>
        PARSER = new com.google.protobuf.AbstractParser<SubcribeReq>() {
      @Override
      public SubcribeReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SubcribeReq(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SubcribeReq> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<SubcribeReq> getParserForType() {
      return PARSER;
    }

    @Override
    public SubscribeReqProto.SubcribeReq getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SubcribeReq_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SubcribeReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022SubscribeReq.proto\"W\n\013SubcribeReq\022\020\n\010s" +
      "ubReqID\030\001 \001(\005\022\020\n\010userName\030\002 \001(\t\022\023\n\013produ" +
      "ctName\030\003 \001(\t\022\017\n\007address\030\004 \003(\tB2\n\035com.via" +
      "gra.chapter08.protobufB\021SubscribeReqProt" +
      "ob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_SubcribeReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SubcribeReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SubcribeReq_descriptor,
        new String[] { "SubReqID", "UserName", "ProductName", "Address", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
