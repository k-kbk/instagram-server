package com.group.instagramserver.domain.member.dto

import com.group.instagramserver.domain.member.entity.Member

data class MemberProfileResponse(
    var name: String,
    var username: String,
    var introduction: String,
    var profileImage: String?,
) {

    companion object {
        fun from(member: Member): MemberProfileResponse {
            return MemberProfileResponse(
                member.name,
                member.username,
                member.introduction,
                member.profileImage
            )
        }
    }
}
