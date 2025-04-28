package com.example.backend_spaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_spaces.model.Member;
import com.example.backend_spaces.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Lấy danh sách hội viên theo gymId
    @GetMapping("/{gymId}")
    public ResponseEntity<List<Member>> getMembersByGym(@PathVariable String gymId) {
        List<Member> members = memberService.getMembersByGym(gymId);
        return ResponseEntity.ok(members);
    }

    // Tạo mới hội viên
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member memberRequest) {
        Member member = memberService.createMember(memberRequest);
        return ResponseEntity.ok(member);
    }

    // Lấy hội viên theo ID
    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật hội viên
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Member memberRequest) {
        Member updated = memberService.updateMember(id, memberRequest);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa hội viên
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable String id) {
        boolean deleted = memberService.deleteMember(id);
        return deleted ? ResponseEntity.ok("Hội viên đã được xóa") : ResponseEntity.notFound().build();
    }
}
