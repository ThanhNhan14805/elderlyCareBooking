package com.yo.elderlycarebooking.mapper;

import com.yo.elderlycarebooking.domain.entity.CareRecipient;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientRequest;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CareRecipientMapper {

    // 1. Chuyển CareRecipient (Entity) -> CareRecipientResponse (DTO)
    @Mapping(source = "id", target = "recipientId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.username", target = "username")
    @Mapping(source = "customer.fullName", target = "customerName")
    @Mapping(source = "customer.phoneNumber", target = "phoneNumber")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(target = "age", expression = "java(recipient.getYearOfBirth() != null ? java.time.Year.now().getValue() - recipient.getYearOfBirth() : null)")
    CareRecipientResponse toResponse(CareRecipient recipient);

    // 2. Chuyển CareRecipientRequest (DTO) -> CareRecipient (Entity)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "mobilityLevel", expression = "java(request.mobilityLevel() != null ? request.mobilityLevel() : com.yo.elderlycarebooking.domain.enumm.MobilityEnum.INDEPENDENT)")
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    CareRecipient toEntity(CareRecipientRequest request);

    // 3. Cập nhật CareRecipient (Entity) từ CareRecipientRequest (DTO)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromRequest(CareRecipientRequest request, @MappingTarget CareRecipient recipient);
}
