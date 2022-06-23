package dev.intermediatebox.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  private String name;
  private long size;
  private String type;
}
