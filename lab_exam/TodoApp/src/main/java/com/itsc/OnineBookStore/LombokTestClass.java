package com.itsc.OnineBookStore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LombokTestClass {
  @Getter private String name;
  @Setter @Getter private String email;
}
