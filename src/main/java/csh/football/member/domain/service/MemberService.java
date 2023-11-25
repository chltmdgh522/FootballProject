package csh.football.member.domain.service;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.password.ForgotPassword;
import csh.football.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public String save(Member member){
        //아이디 중복 방지
        Optional<Member> fmember = memberRepository.findByLoginId(member.getLoginId());
        if (fmember.isPresent()) {
            return "loginId";
        }
        //이메일 중복 방지
        Optional<Member> emember = memberRepository.findByEmail(member.getEmail());
        if (emember.isPresent()) {
            return "email";
        }
        member.setId(UUID.randomUUID().toString());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);

        return null;
    }

    public Member findByMemberId(String id){
        return memberRepository.findByMemberId(id).orElse(null);
    }

    public void updatePassword(String id, String newPassword){
        memberRepository.updatePassword(id, newPassword);
    }

    public String findIdEmail(ForgotPassword forgotPassword){
        Optional<Member> fmember = memberRepository.findByLoginId(forgotPassword.getLoginId());
        if (fmember.isEmpty()) {
            return "loginId";
        }
        //이메일 중복 방지
        Optional<Member> emember = memberRepository.findByEmail(forgotPassword.getEmail());
        if (emember.isEmpty()) {
            return "email";
        }
        return null;
    }
}
