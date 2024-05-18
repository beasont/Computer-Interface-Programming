package com.NWAS.API.CommunityMembers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/members")
public class CommunityMemberController {

    private final CommunityMemberService communityMemberService;

    @Autowired
    public CommunityMemberController(CommunityMemberService communityMemberService) {
        this.communityMemberService = communityMemberService;
    }

    // endpoint to retrieve all community members
    @GetMapping
    public List<CommunityMember> getAllCommunityMembers() {
        return communityMemberService.getAllCommunityMembers();
    }

    // endpoint to retrieve community members by age range
    @GetMapping(params = {"minAge", "maxAge"})
    public List<CommunityMember> getCommunityMembersByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return communityMemberService.getCommunityMembersByAgeRange(minAge, maxAge);
    }

    // endpoint to retrieve a specific community member by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommunityMember> getCommunityMemberById(@PathVariable("id") String id) {
        CommunityMember communityMember = communityMemberService.getCommunityMemberById(id);
        return new ResponseEntity<>(communityMember, HttpStatus.OK);
    }

    // endpoint to add a new community member
    @PostMapping("/add")
    public ResponseEntity<CommunityMember> addCommunityMember(@RequestBody CommunityMember communityMember) {
        CommunityMember newCommunityMember = communityMemberService.addCommunityMember(communityMember);
        return new ResponseEntity<>(newCommunityMember, HttpStatus.CREATED);
    }

    // endpoint to update a specific community member by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CommunityMember> updateCommunityMember(@PathVariable("id") String id, @RequestBody CommunityMember communityMember) {
        CommunityMember updatedCommunityMember = communityMemberService.updateCommunityMember(id, communityMember);
        return new ResponseEntity<>(updatedCommunityMember, HttpStatus.OK);
    }

    // endpoint to delete a specific community member by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommunityMember(@PathVariable("id") String id) {
        communityMemberService.deleteCommunityMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // endpoint to register multiple new community members
    @PostMapping("/add/multiple")
    public ResponseEntity<List<CommunityMember>> addMultipleCommunityMembers(@RequestBody List<CommunityMember> members) {
        List<CommunityMember> newMembers = communityMemberService.addMultipleCommunityMembers(members);
        return new ResponseEntity<>(newMembers, HttpStatus.CREATED);
    }

    // endpoint to update contact information for multiple community members
    @PutMapping("/update/multiple")
    public ResponseEntity<List<CommunityMember>> updateMultipleCommunityMembers(@RequestBody List<CommunityMember> members) {
        List<CommunityMember> updatedMembers = communityMemberService.updateMultipleCommunityMembers(members);
        return ResponseEntity.ok(updatedMembers);
    }

    // endpoint to unregister multiple community members by ID
    @DeleteMapping("/delete/multiple")
    public ResponseEntity<Void> deleteMultipleCommunityMembers(@RequestBody List<String> ids) {
        communityMemberService.deleteMultipleCommunityMembers(ids);
        return ResponseEntity.noContent().build();
    }
}
