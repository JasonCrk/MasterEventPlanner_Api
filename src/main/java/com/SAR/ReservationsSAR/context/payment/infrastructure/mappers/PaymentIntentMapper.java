package com.SAR.ReservationsSAR.context.payment.infrastructure.mappers;

import com.SAR.ReservationsSAR.context.payment.domain.PaymentIntent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentIntentMapper {

    PaymentIntentMapper INSTANCE = Mappers.getMapper(PaymentIntentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "metadata", target = "metadata")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "clientSecret", target = "clientSecret")
    @Mapping(source = "status", target = "status")
    PaymentIntent toDomain(com.stripe.model.PaymentIntent payment);

}
