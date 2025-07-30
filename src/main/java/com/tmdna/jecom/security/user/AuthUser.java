package com.tmdna.jecom.security.user;
import com.tmdna.jecom.common.Role;

import java.util.List;

public record AuthUser(String userId, List<Role> roles) {}
