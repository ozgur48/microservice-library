package com.turkcell.memberservice.application.dto;

import java.util.UUID;

public record MemberDetails(String name,
                            String email,
                            String phone,
                            String address,
                            MemberShipLevelDto memberShipLevel,
                            MemberStatusDto memberStatus) {
}
