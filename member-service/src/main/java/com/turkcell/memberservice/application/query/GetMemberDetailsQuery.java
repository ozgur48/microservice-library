package com.turkcell.memberservice.application.query;

import com.turkcell.memberservice.application.dto.MemberDetails;
import com.turkcell.memberservice.application.dto.MemberShipLevelDto;
import com.turkcell.memberservice.application.dto.MemberStatusDto;
import com.turkcell.memberservice.core.cqrs.Query;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetMemberDetailsQuery(@NotNull UUID id) implements Query<MemberDetails> {
}
