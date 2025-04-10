package org.isdb.StudentCRUD.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ResourceOwnershipService {

	public boolean isOwner(UserDetails userDetails, String resourceId) {
		// In a real application, this would check a database
		// to see if the current user owns the resource

		// Example implementation:
		String username = userDetails.getUsername();
		// Simulate a check - in reality, you'd query a database
		return resourceId.startsWith(username + "-") || "admin".equals(username);
	}
}
