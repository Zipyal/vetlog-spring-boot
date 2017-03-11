package com.jos.dem.vetlog.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.WebDataBinder

import com.jos.dem.vetlog.command.Command
import com.jos.dem.vetlog.command.PetLogCommand

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
@RequestMapping("/petlog")
class PetLogController {

  @Autowired
  PetLogValidator petLogValidator
  @Autowired
  PetService petService

  Logger log = LoggerFactory.getLogger(this.class)

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.addValidators(petLogValidator)
  }

  @RequestMapping(method = GET, value = "/create")
  ModelAndView create(){
    ModelAndView modelAndView = new ModelAndView('petlog/create')
    Command petLogCommand = new PetLogCommand()
    modelAndView.addObject('petLogCommand', petLogCommand)
    modelAndView
  }

  @RequestMapping(method = POST, value = "/save")
  ModelAndView save(@Valid PetLogCommand petLogCommand, BindingResult bindingResult) {
    log.info "Creating pet: ${petLogCommand.pet}"
    ModelAndView modelAndView = new ModelAndView('petlog/create')
    User user = userService.getCurrentUser()
    if (bindingResult.hasErrors()) {
      modelAndView.addObject('petLogCommand', petLogCommand)
      return fillModelAndView(modelAndView, user)
    }
    petLogService.save(petLogCommand, user)
    modelAndView.addObject('message', localeService.getMessage('petLog.created'))
    petLogCommand = new PetLogCommand()
    modelAndView.addObject('petLogCommand', petLogCommand)
    fillModelAndView(modelAndView, user)
  }

  ModelAndView fillModelAndView(ModelAndView modelAndView){
    modelAndView.addObject('pets', petService.getPetsByUser(user))
    modelAndView
  }

}
