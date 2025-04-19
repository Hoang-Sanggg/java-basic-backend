package com.example.backend_spaces.service;

import java.util.List;

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

    // Tạo mới một hội viên
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // Cập nhật hội viên
    public Member updateMember(String id, Member member) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        existingMember.setFullName(member.getFullName());
        existingMember.setPhone(member.getPhone());
        existingMember.setEmail(member.getEmail());
        return memberRepository.save(existingMember);
    }

    // Xóa hội viên theo ID
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}
