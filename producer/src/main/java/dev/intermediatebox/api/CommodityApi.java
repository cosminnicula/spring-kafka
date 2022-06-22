package dev.intermediatebox.api;

import dev.intermediatebox.entity.Commodity;
import dev.intermediatebox.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/commodity")
public class CommodityApi {
  @Autowired
  CommodityService commodityService;

  @GetMapping()
  public List<Commodity> getAllCommodities() {
    return commodityService.createDummyCommodities();
  }
}
