package com.yo.elderlycarebooking.mapper;

import com.yo.elderlycarebooking.domain.entity.Caregiver;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CaregiverMapper {

    // 1. Chuyển Caregiver (Entity) + User (Entity) -> CaregiverResponse (DTO)
    @Mapping(source = "id", target = "caregiverId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.email", target = "email")
    CaregiverResponse toResponse(Caregiver caregiver);

    // 2. Chuyển CaregiverRequest (DTO) -> Caregiver (Entity)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "avgRating", expression = "java(new java.math.BigDecimal(\"5.00\"))")
    @Mapping(target = "totalJobsDone", constant = "0")
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    @Mapping(target = "isAvailable", expression = "java(request.isAvailable() != null ? request.isAvailable() : true)")
    Caregiver toEntity(CaregiverRequest request);

    // 3. Cập nhật Caregiver (Entity) từ CaregiverRequest (DTO) khi Update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "avgRating", ignore = true)
    @Mapping(target = "totalJobsDone", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromRequest(CaregiverRequest request, @MappingTarget Caregiver caregiver);
}
