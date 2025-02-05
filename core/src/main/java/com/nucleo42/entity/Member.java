package com.nucleo42.entity;


import java.util.Objects;

public class Member {
    private User user;
    private Role role;

    public Member(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public Member() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(user, member.user) && Objects.equals(role, member.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }

    @Override
    public String toString() {
        return "Member{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
