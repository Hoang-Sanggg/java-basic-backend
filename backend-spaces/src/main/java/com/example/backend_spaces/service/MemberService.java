package com.example.backend_spaces.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_spaces.model.Member;
import com.example.backend_spaces.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Lấy danh sách hội viên theo gymId
    public List<Member> getMembersByGym(String gymId) {
        return memberRepository.findByGymId(gymId);
    }

    // Tạo hội viên mới
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // Tìm hội viên theo ID
    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    // Cập nhật hội viên
    public Member updateMember(String id, Member memberRequest) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setFullName(memberRequest.getFullName());
            member.setAge(memberRequest.getAge());
            member.setPhone(memberRequest.getPhone());
            member.setEmail(memberRequest.getEmail());
            member.setMembershipType(memberRequest.getMembershipType());
            member.setGymId(memberRequest.getGymId());
            return memberRepository.save(member);
        }
        return null;
    }

    // Xóa hội viên
    public boolean deleteMember(String id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
